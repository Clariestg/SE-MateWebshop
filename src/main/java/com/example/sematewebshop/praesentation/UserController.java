package com.example.sematewebshop.praesentation;

import com.example.sematewebshop.applikation.UserService;
import com.example.sematewebshop.domain.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean success = userService.login(request.getUsername(), request.getPassword());
        if (success) {
            return ResponseEntity.ok("Login erfolgreich");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login fehlgeschlagen");
        }
    }
}

@PostMapping("/register")
public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    boolean success = userService.register(request.getUsername(), request.getPassword());
    if (success) {
        return ResponseEntity.ok("Registrierung erfolgreich");
    } else {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Benutzername bereits vergeben");
    }
}


