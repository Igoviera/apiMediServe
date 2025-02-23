package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.user.UserDTO;
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

    @PostMapping()
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping
    public List<UserDTO> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id){
        return userService.getByIdUser(id);
    }
}
