package com.mx.orderassignment.controller;

import com.mx.orderassignment.dto.Respuesta;
import com.mx.orderassignment.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
