package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.user.UserRequestDTO;
import com.MediServe.apiMediServe.dto.user.UserMapper;
import com.MediServe.apiMediServe.dto.user.UserResponseDTO;
import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.enums.UserStatus;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.repository.UserRepository;
import com.MediServe.apiMediServe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(UserRole.ADMIN);
        user.setStatus(UserStatus.ATIVO);

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
    @Override
    public UserResponseDTO getByIdUser(Long id) {
       return userMapper.toDTO(userRepository.findById(id)
               .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        return userRepository.findAll().stream()
                .map(user -> userMapper.toDTO(user))
                .collect(Collectors.toList());
    }
}
