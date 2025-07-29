package com.example.gateway.web;

import com.example.gateway.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    // Este es un endpoint de login simulado.
    // En un proyecto real, aquí validarías un usuario y contraseña.
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        // Generamos un token para un usuario de prueba llamado "user"
        String token = jwtUtil.generateToken("user");
        return ResponseEntity.ok(Map.of("token", token));
    }
}