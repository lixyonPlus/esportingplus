package com.panda.esportingplus.hystrix.dashboard.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
//@EnableCircuitBreaker
@EnableTurbine
public class HystrixDashboardServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardServerApplication.class, args);
    }
}
