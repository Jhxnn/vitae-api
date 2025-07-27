package com.vitae_api.controllers;


import com.vitae_api.models.Cv;
import com.vitae_api.services.CvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cv")
@Tag(name = "Curriculos", description = "Gerenciamento dos Curriculos")
public class CvController {

    @Autowired
    CvService cvService;

    @Operation(summary = "Avaliar um Curriculo e salvar no banco")
    @ApiResponse(responseCode = "201", description = "Curriculo avaliado e salvo com sucesso")
    @PostMapping
    public ResponseEntity<Cv> cvResponse(@RequestParam("file") MultipartFile file, @RequestParam("userId")UUID userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(cvService.chatResponse(file, userId));
    }

    @Operation(summary = "Gerar texto a partir de um PDF")
    @ApiResponse(responseCode = "201", description = "Texto gerado com sucesso")
    @PostMapping("/file")
    public ResponseEntity<String> cvToString(@RequestParam("file") MultipartFile file){
        return ResponseEntity.status(HttpStatus.CREATED).body(cvService.cvToString(file));
    }
}
