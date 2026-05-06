package org.example.nhom02_userservice.controllers;

import org.apache.coyote.Response;
import org.example.nhom02_userservice.dtos.UserRegistrationRequest;
import org.example.nhom02_userservice.dtos.UserResponse;
import org.example.nhom02_userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin ("*")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestParam String username, @RequestParam String password) {
        UserResponse userResponse = userService.login(username, password);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tài khoản hoặc mật khẩu");
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register (@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(userService.register(userRegistrationRequest));
    }
}
