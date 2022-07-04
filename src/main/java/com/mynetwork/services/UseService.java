package com.mynetwork.services;

import com.mynetwork.dtos.GenericResponse;
import com.mynetwork.entities.UserLogin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UseService {
    public List<UserLogin> findAllUsers();

    GenericResponse<String> saveUserLogin(UserLogin userDetails);

    GenericResponse<String> verifyUser(String userName, String verificationCode);

    GenericResponse<Boolean> isUserNameAvailable(String userName);
}
