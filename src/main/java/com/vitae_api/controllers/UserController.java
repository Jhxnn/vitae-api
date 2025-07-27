package com.vitae_api.controllers;


import com.vitae_api.dtos.LoginDto;
import com.vitae_api.dtos.UserDto;
import com.vitae_api.models.User;
import com.vitae_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Usuarios", description = "Gerenciamento dos Usuarios")
public class UserController {



    @Autowired
    UserService userService;

    @Operation(summary = "Listar todos os Usuarios")
    @ApiResponse(responseCode = "200", description = "Usuarios listados com sucesso")
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @Operation(summary = "Logar um Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginDto));
    }

    @Operation(summary = "Listar Usuario por ID")
    @ApiResponse(responseCode = "200", description = "Usuario listado com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @Operation(summary = "Criar um Usuario")
    @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }


}
