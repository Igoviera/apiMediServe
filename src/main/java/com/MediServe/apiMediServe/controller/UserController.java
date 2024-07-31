package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
}
