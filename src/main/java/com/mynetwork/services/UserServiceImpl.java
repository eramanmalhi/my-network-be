package com.mynetwork.services;

import com.mynetwork.utils.MyNetworkConstants;
import com.mynetwork.dtos.GenericResponse;
import com.mynetwork.entities.UserLogin;
import com.mynetwork.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UseService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<UserLogin> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public GenericResponse<String> saveUserLogin(UserLogin userLogin) {
        Optional<UserLogin> existingUser = userRepo.getByUserName(userLogin.getUserName());
        if (existingUser.isPresent()) {
            return new GenericResponse<String>(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), MyNetworkConstants.REG_EXIST);
        } else {
            return new GenericResponse<String>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), userRepo.save(userLogin) == null ? MyNetworkConstants.REG_FAILED : MyNetworkConstants.REG_SUCCESS);
        }
    }
    @Override
    public GenericResponse<String> verifyUser(String userName, String verificationCode) {
        Optional<UserLogin> user = userRepo.getByUserName(userName);
        if(user.isPresent()){
            if(user.get().isVerified()) {
                return new GenericResponse<String>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), MyNetworkConstants.USER_ALREADY_VERIFIED);
            }
            else {
                if (user.get().getVerificationCode().equalsIgnoreCase(verificationCode)) {
                    user.get().setVerified(true);
                    user.get().setActive(true);
                    user.get().setComments("User Verified Successfully");
                    userRepo.save(user.get());
                    return new GenericResponse<String>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), MyNetworkConstants.USER_VERIFY_SUCCESS);
                } else {
                    return new GenericResponse<String>(HttpStatus.EXPECTATION_FAILED.value(), HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), MyNetworkConstants.USER_VERIFY_FAILED);
                }
            }
        }
        else{
            return new GenericResponse<String>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), MyNetworkConstants.USER_NOT_FOUND);
        }
    }

    @Override
    public GenericResponse<Boolean> isUserNameAvailable(String userName) {
        boolean exists=userRepo.existsByUserName(userName);
        return new GenericResponse<Boolean>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), !(exists));
    }
}
