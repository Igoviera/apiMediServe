package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getByIdUser(Long id);
    List<User> getAllUser();
}
