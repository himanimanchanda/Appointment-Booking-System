package com.appointment.userservice.Controller;

import com.appointment.userservice.Repository.UserRepository;
import com.appointment.userservice.Service.UserService;
import com.appointment.userservice.dto.LoginRequest;
import com.appointment.userservice.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
private final UserRepository usp;
private final UserService uss;



 public AuthController(UserRepository usp,UserService uss){
     this.usp=usp;
     this.uss=uss;
 }
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest credentials){
        String email=credentials.getEmail();
        String password=credentials.getPassword();
        User userdata=uss.login(email,password);

        return userdata;

    }
}
