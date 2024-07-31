package com.MediServe.apiMediServe.service.imp;

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

    @Override
    public User createUser(User user) {
        return userRespository.save(user);
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
