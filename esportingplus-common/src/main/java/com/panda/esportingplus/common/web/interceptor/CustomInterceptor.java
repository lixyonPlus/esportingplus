package com.panda.esportingplus.common.web.interceptor;

import com.panda.esportingplus.common.web.UserSessionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
@ConditionalOnProperty(value = "custom.interceptor", havingValue = "true")
public class CustomInterceptor implements HandlerInterceptor {

    /**
     * 完成整个请求之后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
    }

    /**
     * 进入controller方法之后，渲染视图之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object arg2, ModelAndView arg3) throws Exception {
        UserSessionContext.remove();
    }

    /**
     * 进入controller方法之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String authorization = request.getHeader("Authorization");
        log.debug(" >> authorization:[{}],URI:[{}]", authorization, request.getRequestURI());
        UserSessionContext.setToken(authorization);
        return true;
    }
}