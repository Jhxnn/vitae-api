package com.vitae_api.controllers;


import com.vitae_api.models.Cv;
import com.vitae_api.services.CvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cv")
@Tag(name = "Curriculos", description = "Gerenciamento dos Curriculos")
public class CvController {


    @Autowired
    CvService cvService;


    @Operation(summary = "Avaliar um Curriculo e Salvar no banco")
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

    @Operation(summary = "Listar os Curriculos com maior nota em ordem")
    @ApiResponse(responseCode = "200", description = "Curriculos com maior nota listados com sucesso")
    @GetMapping("/grade")
    public ResponseEntity<List<Cv>> findBigGrade(){
        return ResponseEntity.status(HttpStatus.OK).body(cvService.listBigGrade());
    }

    @Operation(summary = "Listar os Curriculos do Usuario")
    @ApiResponse(responseCode = "200", description = "Curriculos do usuario listados com sucesso")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Cv>> findByUser(@PathVariable(name = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(cvService.userCvs(id));
    }

    @Operation(summary = "Deletar Curriculo")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Cv>> deleteCv(@PathVariable(name = "id")UUID id){
        cvService.deleteCv(id);
        return ResponseEntity.noContent().build();
    }
}
