package com.movitrack.client;

import com.movitrack.model.client.DBClientConfig;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@Configuration
public class WebClientConfig {

    private final DBClientConfig dbClientConfig = DBClientConfig.MOVIESDB;
    private final WebClient webClient;

    public WebClientConfig() {
        this.webClient = WebClient.builder().baseUrl(dbClientConfig.getHost()).build();
    }

}
