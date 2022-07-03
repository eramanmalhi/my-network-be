package com.mynetwork.controllers;

import com.mynetwork.dtos.GenericResponse;
import com.mynetwork.dtos.VerifyUserDto;
import com.mynetwork.entities.UserLogin;
import com.mynetwork.services.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class RegisterVerifyController {

    @Autowired
    private UseService userService;
    @PostMapping("/submitNewUser")
    public GenericResponse<String> saveUser(@RequestBody UserLogin userLogin){
        return userService.saveUserLogin(userLogin);
    }
    @PostMapping("/verifyUser")
    public GenericResponse<String> verifyUser(@RequestBody(required = true) VerifyUserDto verifyUserDto){
        return userService.verifyUser(verifyUserDto.getUserName(), verifyUserDto.getVerificationCode());
    }
}