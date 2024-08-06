package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.UserDTO;
import com.MediServe.apiMediServe.dto.mapper.UserMapper;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.repository.UserRespository;
import com.MediServe.apiMediServe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRespository userRespository;
    private final UserMapper userMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return userMapper.toDTO(userRespository.save(userMapper.toEntity(userDTO)));
    }
    @Override
    public User getByIdUser(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return userRespository.findAll();
    }
}
