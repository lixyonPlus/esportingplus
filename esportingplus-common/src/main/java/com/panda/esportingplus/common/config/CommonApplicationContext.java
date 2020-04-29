package com.panda.esportingplus.common.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 **/
@Component
public class CommonApplicationContext implements ApplicationContextAware {

    private static org.springframework.context.ApplicationContext context;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> cls){
        return context.getBean(cls);
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
