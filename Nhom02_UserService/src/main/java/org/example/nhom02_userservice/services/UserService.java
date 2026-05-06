package org.example.nhom02_userservice.services;

import org.example.nhom02_userservice.dtos.UserRegistrationRequest;
import org.example.nhom02_userservice.dtos.UserResponse;

public interface UserService {
    UserResponse getUser(Long id);
    UserResponse login(String username, String password);
    UserResponse register(UserRegistrationRequest userRegistration);
}
