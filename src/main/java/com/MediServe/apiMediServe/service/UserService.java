package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.user.UserRequestDTO;
import com.MediServe.apiMediServe.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getByIdUser(Long id);
    List<UserResponseDTO> getAllUser();
}
