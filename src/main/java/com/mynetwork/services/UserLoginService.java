package com.mynetwork.services;

import com.mynetwork.dtos.GenericResponse;
import com.mynetwork.dtos.LoginRequest;
import com.mynetwork.dtos.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginService {

    GenericResponse<LoginResponse> validateLogin(LoginRequest request);
}
