package com.example.aijeju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class MyService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<String> getDataFromReactApp() {
        String reactAppUrl = "http://localhost:3000/main";
        return webClientBuilder.build()
                .get()
                .uri(reactAppUrl)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(WebClientResponseException.class, ex -> {
                    System.err.println("Error: " + ex.getStatusCode());
                    System.err.println("Body: " + ex.getResponseBodyAsString());
                });
    }
}
