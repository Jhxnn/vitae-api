package com.vitae_api.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    @PostConstruct
    public void initWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com")
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<String> generateText(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("role", "user", "parts", List.of(Map.of("text", prompt)))
                )
        );

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/models/gemini-1.5-flash:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        var content = (Map<String, Object>) candidates.get(0).get("content");
                        var parts = (List<Map<String, String>>) content.get("parts");
                        return parts.get(0).get("text");
                    }
                    return "Sem resposta do Gemini.";
                });
    }
}
