package com.vitae_api.services;


import com.vitae_api.models.Cv;
import com.vitae_api.repositories.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvService {


    @Autowired
    UserService userService;

    @Autowired
    CvRepository cvRepository;


    public Cv createCv(Cv cv){

    }

}
