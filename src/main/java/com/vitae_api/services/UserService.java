package com.vitae_api.services;


import com.vitae_api.dtos.LoginDto;
import com.vitae_api.dtos.UserDto;
import com.vitae_api.exceptions.BadRequestException;
import com.vitae_api.exceptions.ConflictException;
import com.vitae_api.models.User;
import com.vitae_api.repositories.UserRepository;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User createUser(UserDto userDto) {
        User user = new User();
        if(userRepository.existsByEmail(userDto.email())){
            throw new ConflictException("This email already exists");
        }
        BeanUtils.copyProperties(userDto, user);
        return userRepository.save(user);
    }



    public User login(LoginDto loginDto){
        if(userRepository.existsByEmail(loginDto.email()) && userRepository.existsByName(loginDto.name())){
            return userRepository.findByEmail(loginDto.email());
        }
        throw new BadRequestException("Nome ou Email invalidos");
    }

    public User findById(UUID id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
