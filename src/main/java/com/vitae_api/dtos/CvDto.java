package com.vitae_api.dtos;

import java.util.UUID;

public record CvDto (UUID userId,  double grade, String geminiResponse, String url){
}
