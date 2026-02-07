package com.appointment.users.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class LoginResponse {
    private String Token;
    private UserResponse user;
    public LoginResponse(String Token,UserResponse user){
        this.Token=Token;
        this.user=user;
    }
}
