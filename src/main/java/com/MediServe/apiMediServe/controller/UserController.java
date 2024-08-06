package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.UserDTO;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
}
