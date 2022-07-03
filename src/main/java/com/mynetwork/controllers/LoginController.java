package com.mynetwork.controllers;

import com.mynetwork.dtos.GenericResponse;
import com.mynetwork.dtos.LoginRequest;
import com.mynetwork.dtos.LoginResponse;
import com.mynetwork.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @Autowired
    UserLoginService loginService;

    @PostMapping
    public GenericResponse<LoginResponse> validateLogin(@RequestBody LoginRequest request){
        return loginService.validateLogin(request);
    }
}
