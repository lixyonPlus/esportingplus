package com.panda.esportingplus.common.tools;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.common.constant.SecurityConstants;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.enums.PlatformEnum;
import com.panda.esportingplus.common.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

/**
 * 描述
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @updatetor
 */
public class HttpUtils {

    private static final Logger LOOGER = LoggerFactory.getLogger(HttpUtils.class);

    /*public static HttpEntity buildParam(Object... params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String json = FastJsonUtils.toJson(params);

        return new HttpEntity<>(json, headers);
    }*/

    public static HttpEntity buildParam(Object params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String json = FastJsonUtils.toJson(params);

        return new HttpEntity<>(json, headers);
    }

    public static HttpEntity buildParam_ApplicationJson(Object params) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObject = FastJsonUtils.fromJson(FastJsonUtils.toJson(params), JSONObject.class);
        if (jsonObject != null) {
            jsonObject.forEach((k, v) -> multiValueMap.add(k, v.toString()));
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(multiValueMap, headers);

        return request;
    }

    public static HttpEntity buildJsonParam(String json) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return new HttpEntity<>(json, headers);
    }

    public static HttpEntity buildParamWithSnake(Object params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String json = JacksonUtils.toJsonWithSnake(params);

        return new HttpEntity<>(json, headers);
    }

    public static HttpEntity buildParam(String token, Object params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add(SecurityConstants.AUTHORIZATION, token);
        String json = StringUtils.EMPTY;
        if (params != null) {
            json = FastJsonUtils.toJson(params);
        }

        return new HttpEntity<>(json, headers);
    }

    public static HttpEntity buildParam(String token, String params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add(SecurityConstants.AUTHORIZATION, token);
        return new HttpEntity<>(params, headers);
    }

    public static HttpEntity buildImgParam(MultipartFile imgFile, File tempFile) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        if (null != imgFile && !imgFile.isEmpty()) {
            String cd = "filename=" + imgFile.getOriginalFilename();
            headers.add("Content-Disposition", cd);

            FileSystemResource fileSystemResource = new FileSystemResource(tempFile);
            form.add("imgFile", fileSystemResource);
        }

        return new HttpEntity<>(form, headers);
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(header);
    }

    /**
     * 通过response响应给页面
     *
     * @param response
     * @return
     * @author Orochi-Yzh
     * @dateTime 2018/3/20 18:06
     * @updatetor
     */
    public static void writeToResponse(ServletResponse response, BizExceptionEnum responsePacketEnum) {
        PrintWriter writer = null;
        ResponsePacket result;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ObjectMapper mapper = new ObjectMapper();

        try {
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");

            writer = httpResponse.getWriter();
            result = ResponsePacket.onError(responsePacketEnum);
            writer.write(mapper.writeValueAsString(result));

        } catch (Exception ignored) {
            throw new BusinessException(BizExceptionEnum.INTERNAL_SERVER_ERROR);
        } finally {
            mapper = null;
            if (null != writer) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void writeToResponse(ServletResponse response, Object obj) {
        PrintWriter writer = null;
        ResponsePacket result;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ObjectMapper mapper = new ObjectMapper();

        try {
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");

            writer = httpResponse.getWriter();
            result = ResponsePacket.onSuccess(obj);
            writer.write(mapper.writeValueAsString(result));

        } catch (Exception ignored) {
            throw new BusinessException(BizExceptionEnum.INTERNAL_SERVER_ERROR);
        } finally {
            mapper = null;
            if (null != writer) {
                writer.flush();
                writer.close();
            }
        }
    }

    /**
     * 判断请求的来源
     *
     * @param request
     * @return {@link com.panda.esportingplus.common.enums.PlatformEnum}
     */
    public static PlatformEnum getPlatform(HttpServletRequest request){
        // 获取请求的客户端平台
        String param = request.getParameter("x");
        if (ObjectTools.isNotEmpty(param)) {
            param = param.substring(0, 1);
            return PlatformEnum.getByParam(param);
        }
        // 前端未传这个 x 参数则根据 USER-AGENT 获取, 显然这个方法不太靠谱, 暂时先用着吧
        String header = request.getHeader("USER-AGENT").toLowerCase();
        if (header.contains("android")) {
            return PlatformEnum.Android;
        } else if (header.contains("iphone") || header.contains("ipad")) {
            return PlatformEnum.iOS;
        }
        return PlatformEnum.OTHER;
    }

    public static void main(String[] args) {
        System.out.println("i_280_kh".substring(0, 1));
    }

}
