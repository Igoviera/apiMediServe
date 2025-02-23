package com.MediServe.apiMediServe.dto.user;

import com.MediServe.apiMediServe.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    public UserDTO toDTO(User user){
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    public User toEntity(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }

        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.roles());
        return user;
    }
}
