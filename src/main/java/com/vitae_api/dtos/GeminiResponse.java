package com.vitae_api.dtos;

import java.util.List;

public record GeminiResponse(List<Candidate> candidates) {
    public record Candidate(Content content) {
        public record Content(String role, String text) {}
    }
}
