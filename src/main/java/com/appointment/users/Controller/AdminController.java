package com.appointment.users.Controller;

import com.appointment.users.Security.JWTUtil;
import com.appointment.users.Service.UserService;
import com.appointment.users.dto.UserRegisterRequest;
import com.appointment.users.dto.UserResponse;
import com.appointment.users.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService uss, JWTUtil jwt) {
        this.userService = uss;
    }

    @PostMapping("/onboard")
    public UserResponse onboard(@RequestBody UserRegisterRequest ur) {
        User user = userService.onboardService(ur);
        UserResponse response = new UserResponse(user);
        return response;
    }

    //    admin/allusers-- this api will fetch all the saved users from db and map it to UserResponse dto and return ,it calls getAllUSerService mehod from service
    @GetMapping("/allusers")
    public List<UserResponse> getAllUser() {
        return userService.getAllUserService().stream()
                .map(UserResponse::new).toList();


    }

//    get mapping to find UserBy id
    @GetMapping("/user/{id}")
    public UserResponse getUserById(@PathVariable Long id){

        User user=userService.getUserByIdService(id);
        return new UserResponse(user);
    }
//   delete user by id
    @DeleteMapping("/delete/user/{id}")
    public String deleteUserById(@PathVariable Long id) {
        System.out.println("api hit successfully");

        User user = userService.deleteUserByIdService(id);
        String str = "user with id " + user.getId() + " deleted succesfully ";
        return str;
    }
//    change active status by id
    @PatchMapping("/changeactivestatus/user/{id}")
    public UserResponse changeActivestatus(@PathVariable Long id){

        User user =userService.changeActiveStatusService(id);
        return new UserResponse(user);
        }

    }

