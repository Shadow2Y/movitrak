package com.movitrack.client;

import com.movitrack.model.Item;
import com.movitrack.model.client.DBClientConfig;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class DBClient {

    private final WebClient webClient;
    private final WebClientConfig webClientConfig;
    private final DBClientConfig dbClientConfig;

    public DBClient(WebClientConfig webClientConfig) {
        this.webClientConfig = webClientConfig;
        this.webClient = webClientConfig.getWebClient();
        this.dbClientConfig = webClientConfig.getDbClientConfig();
    }

    public Mono<List<Item>> refreshData() {
        return webClient.get()
                .uri(dbClientConfig.getEndpoint())
                .headers(dbClientConfig.getHeaders())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }


}
