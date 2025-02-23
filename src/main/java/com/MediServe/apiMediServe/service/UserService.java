package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.user.UserDTO;
import com.MediServe.apiMediServe.model.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getByIdUser(Long id);
    List<UserDTO> getAllUser();
}
