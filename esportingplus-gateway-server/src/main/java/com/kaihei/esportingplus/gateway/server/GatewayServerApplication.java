package com.kaihei.esportingplus.gateway.server;

import com.kaihei.esportingplus.common.event.EventBus;
import com.kaihei.esportingplus.common.event.EventBusThreadPoolInitializer;
import com.kaihei.esportingplus.common.thread.pool.AppContextThreadPoolInitializer;
import com.kaihei.esportingplus.common.thread.pool.ThreadPoolManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * 在Spring Cloud微服务系统中，
 * 一种常见的负载均衡方式是，客户端的请求首先经过负载均衡（zuul、Ngnix），<br/>
 * 再到达服务网关（zuul集群），然后再到具体的服务。<br/>
 * Zuul的主要功能是路由转发和过滤器。<br/>
 * 路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。<br/>
 * zuul默认和Ribbon结合实现了负载均衡的功能<br/>
 *
 * @author LiuQing.Qin
 * @date 2018年1月19日 下午2:40:26
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.kaihei.esportingplus.**"})
@ComponentScan(basePackages = {"com.kaihei.esportingplus.**"})
@EnableCircuitBreaker
@RefreshScope
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GatewayServerApplication.class);
        springApplication.addInitializers(new AppContextThreadPoolInitializer(), new EventBusThreadPoolInitializer());
        springApplication.run(args);
    }
}
