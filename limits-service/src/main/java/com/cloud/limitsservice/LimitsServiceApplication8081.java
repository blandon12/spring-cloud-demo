package com.cloud.limitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LimitsServiceApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(LimitsServiceApplication8081.class, args);
    }

}
