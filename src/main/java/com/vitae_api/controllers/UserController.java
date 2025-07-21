package com.vitae_api.controllers;


import com.vitae_api.models.User;
import com.vitae_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {



    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<User> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }


}
