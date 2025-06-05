package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.user.UserDTO;
import com.MediServe.apiMediServe.dto.user.UserMapper;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.repository.UserRespository;
import com.MediServe.apiMediServe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public UserDTO getByIdUser(Long id) {
       return userMapper.toDTO(userRespository.findById(id)
               .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRespository.findAll().stream()
                .map(user -> userMapper.toDTO(user))
                .collect(Collectors.toList());
    }
}
