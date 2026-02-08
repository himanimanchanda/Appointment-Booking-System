package com.appointment.users.Controller;

import com.appointment.users.Security.JWTUtil;
import com.appointment.users.Service.UserService;
import com.appointment.users.dto.UserRegisterRequest;
import com.appointment.users.dto.UserResponse;
import com.appointment.users.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController{
  private final UserService userService;

    public AdminController(UserService uss, JWTUtil jwt){
        this.userService=uss;
    }
    @PostMapping("/onboard")
    public UserResponse onboard(@RequestBody UserRegisterRequest ur){
        User user=userService.onboardService(ur);
        UserResponse response=new UserResponse(user);
        return response;
    }
}
