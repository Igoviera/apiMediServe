package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.user.UserRequestDTO;
import com.MediServe.apiMediServe.dto.user.UserResponseDTO;
import com.MediServe.apiMediServe.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable("id") Long id){
        return userService.getByIdUser(id);
    }
}
