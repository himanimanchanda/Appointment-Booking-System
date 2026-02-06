package com.appointment.userservice.Controller;

import com.appointment.userservice.dto.UserRegisterRequest;
import com.appointment.userservice.entity.User;
import com.appointment.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public User register(@RequestBody UserRegisterRequest request){
        return userService.registerUser(request);
    }
}

