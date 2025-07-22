package com.vitae_api.dtos;

import java.util.List;

public record ChatRequestDto(
        String model,
        List<MessageDto> messages
) {
    public record MessageDto(
            String role,
            String content
    ) {}
}
