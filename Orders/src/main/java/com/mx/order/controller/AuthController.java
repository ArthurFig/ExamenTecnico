package com.mx.order.controller;

import com.mx.order.dto.Respuesta;
import com.mx.order.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Respuesta> login(@RequestBody Map<String, String> user) {
        try {
            String username = user.get("username");
            String password = user.get("password");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            String token = jwtUtil.generateToken(username);

            return ResponseEntity.ok(new Respuesta("Login exitoso", true, token, LocalDateTime.now()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new Respuesta("Credenciales inválidas", false, null, LocalDateTime.now()));
        }
    }
}
