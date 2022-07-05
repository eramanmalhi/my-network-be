package com.mynetwork.dtos;

import com.mynetwork.entities.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String userName;
    private String emailId;
    private String statusCode;
    private String status;
    private UserDetails details;
}
