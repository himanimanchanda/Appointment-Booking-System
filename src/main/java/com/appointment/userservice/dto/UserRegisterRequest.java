package com.appointment.userservice.dto;
import com.appointment.userservice.entity.Role;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String phone;
}
