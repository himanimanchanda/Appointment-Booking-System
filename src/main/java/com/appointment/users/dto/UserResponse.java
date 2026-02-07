package com.appointment.users.dto;

import com.appointment.users.entity.Role;
import com.appointment.users.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private Boolean active;
    public UserResponse(User user){
        this.id=user.getId();
        this.name=user.getName();
         this.email=user.getEmail();
           this.phone= user.getPhone();
           this.role=user.getRole();
            this.active=user.isActive();
    }
}
