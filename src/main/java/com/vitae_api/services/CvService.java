package com.vitae_api.services;


import com.vitae_api.dtos.CvDto;
import com.vitae_api.models.Cv;
import com.vitae_api.models.User;
import com.vitae_api.repositories.CvRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvService {


    @Autowired
    UserService userService;

    @Autowired
    CvRepository cvRepository;


    public Cv createCv(CvDto cvDto){
        Cv cv = new Cv();
        BeanUtils.copyProperties(cvDto, cv);
        User user = userService.findById(cvDto.userId());
        cv.setUser(user);
        return cvRepository.save(cv);
    }


}
