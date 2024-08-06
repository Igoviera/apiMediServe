package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.UserDTO;
import com.MediServe.apiMediServe.model.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    User getByIdUser(Long id);
    List<User> getAllUser();
}
