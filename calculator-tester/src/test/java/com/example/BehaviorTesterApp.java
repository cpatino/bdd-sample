package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BehaviorTesterApp {

    public static void main(String[] args) {
        new SpringApplication(BehaviorTesterApp.class).run(args);
    }
}
