package com.appointment.users.dto;
import com.appointment.users.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String phone;
}
