package com.example.serverinstagram;

import com.example.serverinstagram.configuration.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ServerInstagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerInstagramApplication.class, args);
    }

}
