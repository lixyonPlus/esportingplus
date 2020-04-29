package com.panda.esportingplus.common.tools;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    //application上下文
    private static ApplicationContext context;
    //配置变量
    private static Environment environment;
    //配置上下文
    private static ConfigurableApplicationContext configurableContext;
    //bean注册器
    private static BeanDefinitionRegistry beanDefinitionRegistry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        configurableContext = (ConfigurableApplicationContext) context;
        beanDefinitionRegistry = (DefaultListableBeanFactory) configurableContext.getBeanFactory();
    }

    public static ApplicationContext getContext(){
        return context;
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> tClass){
        return context.getBean(tClass);
    }

    public static Environment getEnvironment(){
        environment = context.getEnvironment();
        return environment;
    }

    /**
     * 注册bean
     * @param beanId 所注册bean的id
     * @param className bean的className，
     *                     三种获取方式：1、直接书写，如：com.mvc.entity.User
     *                                   2、User.class.getName
     *                                   3.user.getClass().getName()
     */
    public static void registerBean(String beanId,String className) {
        // get the BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(className);
        // get the BeanDefinition
        BeanDefinition beanDefinition=beanDefinitionBuilder.getBeanDefinition();
        // register the bean
        beanDefinitionRegistry.registerBeanDefinition(beanId,beanDefinition);
    }

    /**
     * 移除bean
     * @param beanId bean的id
     */
    public static void unregisterBean(String beanId){
        beanDefinitionRegistry.removeBeanDefinition(beanId);
    }
}
