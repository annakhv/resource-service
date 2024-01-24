package com.resource.storage.resource.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${song-service.base-url}")
    String BASE_URL;

    @Bean
    WebClient songWebclient(){
        return WebClient.builder().baseUrl(BASE_URL).build();
    }

}
