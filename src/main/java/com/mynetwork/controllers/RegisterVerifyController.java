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
@CrossOrigin({"*"})
public class RegisterVerifyController {

    @Autowired
    private UseService userService;
    @PostMapping("/submitNewUser")
    public GenericResponse<String> saveUser(@RequestBody UserLogin userLogin){
        return userService.saveUserLogin(userLogin);
    }
    @PostMapping("/verifyUser/{userName}/{verificationCode}")
    public GenericResponse<String> verifyUser(@PathVariable(name = "userName", required = true) String userName, @PathVariable(name = "verificationCode", required = true) String verificationCode){
        return userService.verifyUser(userName, verificationCode);
    }
    @GetMapping("/isUserNameAvailable/{userName}")
    public GenericResponse<Boolean> isUserNameAvailable(@PathVariable(name="userName") String userName){
        return userService.isUserNameAvailable(userName);
    }
}
