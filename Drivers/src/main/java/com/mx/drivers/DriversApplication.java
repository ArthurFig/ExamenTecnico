package com.mx.drivers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class DriversApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriversApplication.class, args);
    }

}
