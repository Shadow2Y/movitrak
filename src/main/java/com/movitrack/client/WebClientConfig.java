package com.movitrack.client;

import com.movitrack.model.client.DBClientConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;


@Slf4j
@Getter
@Configuration
public class WebClientConfig {

    private final DBClientConfig dbClientConfig = DBClientConfig.MOVIESDB;
    private final WebClient webClient;

    public WebClientConfig() {
        log.info("Initiating WebClient with config :: {}",dbClientConfig);
        this.webClient = WebClient.builder()
                .baseUrl(dbClientConfig.getHost())
                .filter(logRequestAndResponseCurl())
                .filter(logRequestAndResponseCurl())
                .build();
    }

    private ExchangeFilterFunction logRequestAndResponseCurl() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            StringBuilder curlCmd = new StringBuilder("curl -X ")
                    .append(request.method())
                    .append(" '").append(request.url()).append("'");

            request.headers().forEach((name, values) -> {
                for (String value : values) {
                    curlCmd.append(" -H '").append(name).append(": ").append(value).append("'");
                }
            });

            log.info("\n=== cURL Request ===\n {}",curlCmd);

            return Mono.just(request);
        }).andThen(ExchangeFilterFunction.ofResponseProcessor(response -> {
            StringBuilder sb = new StringBuilder("\n=== Response ===\n")
                    .append("Status: ").append(response.statusCode()).append("\n");

            response.headers().asHttpHeaders().forEach((name, values) -> {
                sb.append(name).append(": ").append(String.join(", ", values)).append("\n");
            });

            // Read the body as a String, then rebuild the response
            return response.bodyToMono(String.class)
                    .flatMap(body -> {
                        sb.append("Body: ").append(body).append("\n");
                        log.info(sb.toString());
                        return Mono.just(
                                ClientResponse.create(response.statusCode())
                                        .headers(headers -> headers.addAll(response.headers().asHttpHeaders()))
                                        .body(body)
                                        .build()
                        );
                    });
        }));
    }


}
