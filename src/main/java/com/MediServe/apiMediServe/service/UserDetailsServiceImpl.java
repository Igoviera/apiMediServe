package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RecordNotFoundException("Usuário não encontrado"));
    }

}
