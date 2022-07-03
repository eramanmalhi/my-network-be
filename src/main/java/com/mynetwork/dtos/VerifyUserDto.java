package com.mynetwork.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyUserDto {
    private String userName;
    private String verificationCode;
}
