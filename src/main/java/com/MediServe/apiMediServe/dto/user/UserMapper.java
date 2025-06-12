package com.MediServe.apiMediServe.dto.user;

import com.MediServe.apiMediServe.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    public UserResponseDTO toDTO(User user){
        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User toEntity(UserRequestDTO userRequestDTO){
        if (userRequestDTO == null){
            return null;
        }

        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setEmail(userRequestDTO.email());
        user.setPassword(userRequestDTO.password());
        user.setRole(userRequestDTO.roles());
        return user;
    }
}
