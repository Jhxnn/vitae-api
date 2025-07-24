package com.vitae_api.controllers;


import com.vitae_api.dtos.GeminiResponse;
import com.vitae_api.services.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/cv")
public class CvController {


    @Autowired
    CvService cvService;

    @PostMapping
    public ResponseEntity<GeminiResponse.Candidate> cvResponse(@RequestParam("file") MultipartFile file){
        return ResponseEntity.noContent().build();
    }
}
