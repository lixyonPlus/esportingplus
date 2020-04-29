package com.panda.esportingplus.common.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 根据IP地址获取区域信息
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class IpAreaUtils {

    private static final Logger logger = LoggerFactory.getLogger(IpAreaUtils.class);

    public static String getAreaInfo(String ipAddress) {
        logger.debug("ipAddress : {} ", ipAddress);
        String requestUrl = "http://opendata.baidu.com/api.php?query=%s&co=&resource_id=6006&t=1433920989928&ie=utf8&oe=utf-8&format=json";
        String area = "充值区域";
        try {
            URL url = new URL(String.format(requestUrl, ipAddress));
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            JSONObject jsStr = JSONObject.parseObject(result.toString());
            JSONArray jsData = (JSONArray) jsStr.get("data");
            //位置
            if (jsData.size() > 0) {
                JSONObject data = (JSONObject) jsData.get(0);
                area = (String) data.get("location");
            }
        } catch (IOException e) {
            logger.warn("读取失败 : {}", e.getMessage());
        }
        logger.debug("area : {} ", area);
        return area;

    }

}
