package com.mynetwork.services;

import com.mynetwork.utils.MyNetworkConstants;
import com.mynetwork.dtos.GenericResponse;
import com.mynetwork.dtos.LoginRequest;
import com.mynetwork.dtos.LoginResponse;
import com.mynetwork.entities.UserDetails;
import com.mynetwork.entities.UserLogin;
import com.mynetwork.repositories.UserDetailsRepo;
import com.mynetwork.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Override
    public GenericResponse<LoginResponse> validateLogin(LoginRequest request) {
        Optional<UserLogin> user= userRepo.findByUserNameAndPassword(request.getUserName(), request.getPassword());
        if(user.isPresent()){
            UserDetails details=userDetailsRepo.findByUserName(user.get().getUserName());
            LoginResponse response=null;
            if(user.get().isVerified() && user.get().isActive() && !(user.get().isLocked())) {
                response = new LoginResponse(request.getUserName(), user.get().getEmailId(),null, MyNetworkConstants.LOGIN_SUCCESS, details);
            }
            else if(!(user.get().isVerified())){
                response = new LoginResponse(request.getUserName(), null, MyNetworkConstants.USER_NOT_VERIFIED_CODE, MyNetworkConstants.USER_NOT_VERIFIED, null);
            }
            else if(!(user.get().isActive())){
                response = new LoginResponse(request.getUserName(), null,MyNetworkConstants.USER_NOT_ACTIVE_CODE, MyNetworkConstants.USER_NOT_ACTIVE, null);
            }
            else if(user.get().isLocked()){
                response = new LoginResponse(request.getUserName(), null,MyNetworkConstants.USER_LOCKED_CODE, MyNetworkConstants.USER_LOCKED, null);
            }
            return new GenericResponse<LoginResponse>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), response);
        }
        else{
            LoginResponse response=new LoginResponse(request.getUserName(), null,null,MyNetworkConstants.LOGIN_FAILED+" for "+request.getUserName(), null);
            return new GenericResponse<LoginResponse>(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), response);
        }
    }
}
