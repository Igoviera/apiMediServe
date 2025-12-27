package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.AuthRequest;
import com.MediServe.apiMediServe.dto.AuthResponse;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.repository.UserRepository;
import com.MediServe.apiMediServe.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mediServe/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RecordNotFoundException("Usuário não encontrado"));

        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwt));

    }
}
