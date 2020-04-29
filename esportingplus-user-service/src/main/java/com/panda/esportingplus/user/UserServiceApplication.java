package com.panda.esportingplus.user;

import com.panda.esportingplus.common.event.EventBusThreadPoolInitializer;
import com.panda.esportingplus.common.thread.pool.AppContextThreadPoolInitializer;
import com.maihaoche.starter.mq.annotation.EnableMQConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableMQConfiguration
@ComponentScan(basePackages = {"com.panda.esportingplus.**"})
@EnableFeignClients(basePackages = {"com.panda.esportingplus.**"})

public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserServiceApplication.class);
        springApplication.addInitializers(new AppContextThreadPoolInitializer(), new EventBusThreadPoolInitializer());
        springApplication.run(args);
    }
}
