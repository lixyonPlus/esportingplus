package com.kaihei.esportingplus.gateway.server.utils;

import com.kaihei.esportingplus.common.ResponsePacket;
import com.kaihei.esportingplus.common.constant.SecurityConstants;
import com.kaihei.esportingplus.common.enums.BizExceptionEnum;
import com.kaihei.esportingplus.common.tools.FastJsonUtils;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;

public class ZuulResponseUtils {

    public static void failJson(RequestContext ctx, ResponsePacket data, int statusCode) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(statusCode);
        ctx.getResponse().setCharacterEncoding("UTF-8");
        ctx.getResponse().setContentType("application/json; charset=utf-8");
        String responseJson = FastJsonUtils.toJson(data);
        ctx.setResponseBody(responseJson);
    }

    public static void fail(RequestContext ctx, String data, int statusCode) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(statusCode);
        ctx.getResponse().setCharacterEncoding("UTF-8");
        ctx.getResponse().setContentType("application/json; charset=utf-8");
        ctx.setResponseBody(data);
    }

    public static void OK(RequestContext ctx, Object data, String access_token) {
        ctx.setResponseStatusCode(200);
        ctx.setSendZuulResponse(false);
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        if( data != null){
            ctx.setResponseBody(FastJsonUtils.toJson(data));
        }
        ctx.getResponse().setHeader(SecurityConstants.ACCESS_TOKEN, access_token);
        ctx.getResponse().setHeader("Access-Control-Expose-Headers","access_token");
    }

    public static void overRateLimit(RequestContext ctx, String token) {
        ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        ctx.setSendZuulResponse(false);
        ctx.put("rateLimitExceeded", "true");
        //设置开启限流标识
        ctx.set("activeRateLimit:" + token);
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        ctx.setResponseBody(FastJsonUtils.toJson(ResponsePacket.onError(BizExceptionEnum.TOO_MANY_REQUEST)));
    }
}
