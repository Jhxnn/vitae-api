package com.vitae_api.dtos;

import java.util.List;



public record ChatResponseDto(
        List<ChoiceDto> choices
) {
    public record ChoiceDto(
            MessageDto message
    ) {}

    public record MessageDto(
            String role,
            String content
    ) {}
}