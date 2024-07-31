package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
}
