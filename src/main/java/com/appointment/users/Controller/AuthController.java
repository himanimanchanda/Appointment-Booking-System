package com.appointment.users.Controller;

import com.appointment.users.Repository.UserRepository;
import com.appointment.users.Security.JWTUtil;
import com.appointment.users.Service.UserService;
import com.appointment.users.dto.LoginRequest;
import com.appointment.users.dto.LoginResponse;
import com.appointment.users.dto.UserRegisterRequest;
import com.appointment.users.dto.UserResponse;
import com.appointment.users.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository usp;
    private final UserService uss;
    private final JWTUtil jwt;

    //Constructor of Class
    public AuthController(UserRepository usp, UserService uss,JWTUtil jwt) {
        this.usp = usp;
        this.uss = uss;
        this.jwt=jwt;
    }

    // Login Route / auth / login
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest credentials) {
        String email = credentials.getEmail();
        String password = credentials.getPassword();
        User userdata = uss.login(email, password);
// Generating Token Using JWTUtil class method generate token yes
        String token=jwt.generateToken(userdata.getId(),userdata.getRole());

        UserResponse usert=new UserResponse(userdata);
        LoginResponse lr=new LoginResponse(token,usert);
        return lr;

    }

    //    Signup Route / auth / signup
    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserRegisterRequest userdata) {
       User user=uss.signup(userdata);
       UserResponse userp=new UserResponse(user);
       return userp;

    }

}