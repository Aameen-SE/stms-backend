package com.aameen.stms.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String token;
    private String email;
    private String role;

   public LoginResponseDto(String token,String email,String role){
        this.token=token;
        this.email=email;
        this.role=role;
    }


}
