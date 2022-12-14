package com.example.serverinstagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ServerInstagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerInstagramApplication.class, args);
    }

}
