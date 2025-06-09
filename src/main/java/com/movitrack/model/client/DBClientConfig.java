package com.movitrack.model.client;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.function.Consumer;

@Getter
public enum DBClientConfig {

    MOVIESDB("https://sheetdb.io/api/v1/6t6tkopzl04ii","/search?id=2","limit:2"),

    ;

    private String host;
    private String endpoint;
    private Consumer<HttpHeaders> headers;

    DBClientConfig(String host, String endpoint, String... headers) {
        this.host = host;
        this.endpoint = endpoint;
        this.headers = httpHeaders -> {
            for (String header : headers) {
                String[] keyVal = header.split(":", 2);
                if(keyVal.length == 2)
                    httpHeaders.set(keyVal[0], keyVal[1]);
            }
        };
    }

}
