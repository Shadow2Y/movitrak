package com.movitrack.model.client;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;


@Slf4j
@Getter
public enum DBClientConfig {

    MOVIESDB("https://sheetdb.io/api/v1/6t6tkopzl04ii","","","limit:5"),

    ;

    private String host;
    private String endpoint;
    private HttpHeaders defaultHeaders;
    private HashMap<String,String> defaultAttributes;

    DBClientConfig(String host, String endpoint, String defaultHeaders, String attributes) {
        this.host = host;
        this.endpoint = endpoint;
        this.defaultHeaders = new HttpHeaders();
        this.defaultAttributes = new HashMap<>();
        for(String header : defaultHeaders.split(";")) {
            String[] keyVal = header.split(":", 2);
            if(keyVal.length == 2)
                this.defaultHeaders.set(keyVal[0], keyVal[1]);
        }
        for(String attribute : attributes.split(";")) {
            String[] keyVal = attribute.split(":", 2);
            if(keyVal.length == 2)
                this.defaultAttributes.put(keyVal[0], keyVal[1]);
        }
    }

}
