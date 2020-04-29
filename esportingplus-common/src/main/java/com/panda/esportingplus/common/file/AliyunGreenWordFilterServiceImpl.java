package com.panda.esportingplus.common.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.green.model.v20180509.TextScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import com.panda.esportingplus.common.config.AliyunGreenConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service("aliyunGreenWordFilterService")
@Configuration
@EnableConfigurationProperties({AliyunGreenConfig.class})
@ConditionalOnBean(AliyunGreenConfig.class)
public class AliyunGreenWordFilterServiceImpl implements WordFilterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliyunGreenWordFilterServiceImpl.class);

    @Autowired
    private AliyunGreenConfig aliyunGreenConfig;

    /**
     * 阿里云敏感字分析
     * @param works
     * @return
     */
    @Override
    public boolean checkWord(String works) {
        //获取文本扫描请求对象
        TextScanRequest textScanRequest = getTextScanRequest(works);
        HttpResponse httpResponse = null;

        try {
            //请求阿里云
            LOGGER.info("==> AliyunGreenWordFilterServiceImpl checkWord | works={} | textScanRequest={}",
                    works,textScanRequest);
            httpResponse = getIAcsClient().doAction(textScanRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        if(httpResponse != null){
            //分析响应信息
            return analyseAliyunHttpResponse(httpResponse);
        }
        return true;
    }

    private IAcsClient getIAcsClient() throws ClientException {
        String zone = "cn-shanghai";
        String product = "Green";
        IClientProfile profile = DefaultProfile.getProfile(zone, aliyunGreenConfig.getAccesskeyId(), aliyunGreenConfig.getSecret());
        DefaultProfile.addEndpoint(zone, zone, product, aliyunGreenConfig.getUrl());
        LOGGER.info("==> AliyunGreenWordFilterServiceImpl getIAcsClient | AccesskeyId={} | Secret={} | Url={}",
                aliyunGreenConfig.getAccesskeyId(),aliyunGreenConfig.getSecret(),aliyunGreenConfig.getUrl());
        return new DefaultAcsClient(profile);
    }

    /**
     * 组装文本扫描请求对象
     * @param works
     * @reture
     */
    private static TextScanRequest getTextScanRequest(String... works) {
        TextScanRequest textScanRequest = new TextScanRequest();
        // 指定api返回格式
        textScanRequest.setAcceptFormat(FormatType.JSON);
        textScanRequest.setHttpContentType(FormatType.JSON);
        // 指定请求方法
        textScanRequest.setMethod(com.aliyuncs.http.MethodType.POST);
        textScanRequest.setEncoding("UTF-8");
        textScanRequest.setRegionId("cn-shanghai");
        List<Map<String, Object>> tasks = new ArrayList<Map<String, Object>>();
        for (String work: works) {
            Map<String, Object> task1 = new LinkedHashMap<String, Object>();
            task1.put("dataId", UUID.randomUUID().toString());
            task1.put("content", work);
            tasks.add(task1);
        }
        JSONObject data = new JSONObject();
        /**
         * 文本垃圾检测：antispam
         * 关键词检测：keyword
         **/
        data.put("scenes", Arrays.asList("antispam"));
        data.put("tasks", tasks);
        try {
            textScanRequest.setHttpContent(data.toJSONString().getBytes("UTF-8"), "UTF-8", FormatType.JSON);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 请务必设置超时时间
        textScanRequest.setConnectTimeout(3000);
        textScanRequest.setReadTimeout(6000);
        return textScanRequest;
    }

    /**
     * 分析响应信息
     * @param httpResponse
     * @return
     *
     */
    private static boolean analyseAliyunHttpResponse(HttpResponse httpResponse){
        if(httpResponse.isSuccess()){
            JSONObject scrResponse = null;
            try {
                scrResponse = JSON.parseObject(new String(httpResponse.getHttpContent(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (scrResponse != null && 200 == scrResponse.getInteger("code")) {
                JSONArray taskResults = scrResponse.getJSONArray("data");
                for (Object taskResult : taskResults) {
                    if(200 == ((JSONObject)taskResult).getInteger("code")){
                        JSONArray sceneResults = ((JSONObject)taskResult).getJSONArray("results");
                        String content = ((JSONObject)taskResult).getString("content");
                        for (Object sceneResult : sceneResults) {
                            String scene = ((JSONObject)sceneResult).getString("scene");
                            String suggestion = ((JSONObject)sceneResult).getString("suggestion");
                            //处理建议 pass:文本正常，review：需要人工审核，block：文本违规，可以直接删除或者做限制处理
                            if("review".equals(suggestion)||"block".equals(suggestion)){
                                return false;
                            }else if("pass".equals(suggestion)){
                                break;
                            }else{
                                LOGGER.error("阿里云信息核对失败 {}",JSONObject.toJSON(sceneResult));
                            }
                        }
                    }else{
                        LOGGER.error("阿里云信息核对失败{}",JSONObject.toJSON(taskResult));
                    }
                }
            } else {
                LOGGER.warn("阿里云反垃圾文本API调用异常 | scrResponse = {}",JSONObject.toJSON(scrResponse));
            }
        }else{
            LOGGER.warn("阿里云反垃圾文本API调用异常 | httpResponse = {}",JSONObject.toJSON(httpResponse));
            return true;
        }
        return true;
    }

}
