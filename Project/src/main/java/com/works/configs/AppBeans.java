package com.works.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Configuration
public class AppBeans {

    @Bean
    public String uui() {
        return UUID.randomUUID().toString();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
