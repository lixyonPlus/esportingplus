package com.kaihei.esportingplus.gateway.server.controller;

import com.kaihei.esportingplus.common.ResponsePacket;
import com.kaihei.esportingplus.common.enums.BizExceptionEnum;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*/**
 *@Description: 自定义error错误页面
 *
 *@author  Orochi-Yzh
 *@dateTime  2018/8/17 18:16
*/
@RestController
public class ErrorHandlerController implements ErrorController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    /**
     * 出异常后进入该方法，交由下面的方法处理
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }
 
    @RequestMapping("/error")
    public ResponsePacket error(){
        RequestContext ctx = RequestContext.getCurrentContext();
        BizExceptionEnum exceptionEnum = BizExceptionEnum.fromCode(ctx.getResponseStatusCode());
        return ResponsePacket.onError(exceptionEnum);
    }
 
}