package com.movitrack.client;

import com.movitrack.model.Item;
import com.movitrack.model.client.DBClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
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
        log.info("Attributes :: {}",dbClientConfig.getDefaultAttributes().toString());
        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(dbClientConfig.getEndpoint());
                    dbClientConfig.getDefaultAttributes().forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .headers(headers -> headers.addAll(dbClientConfig.getDefaultHeaders()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }


}
