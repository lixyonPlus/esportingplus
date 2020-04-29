package com.panda.esportingplus.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * Zipkin Server
 * {@link zipkin.autoconfigure.collector.kafka.ZipkinKafkaCollectorProperties}
 * @Author LiuQing.Qin
 * @Date 2018/9/6 16:19:20
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
