package org.example.nhom02_userservice.services.impl;

import org.example.nhom02_userservice.dtos.UserRegistrationRequest;
import org.example.nhom02_userservice.dtos.UserResponse;
import org.example.nhom02_userservice.models.User;
import org.example.nhom02_userservice.repositories.UserRepository;
import org.example.nhom02_userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(user -> UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .build())
                .orElse(null);
    }

    @Override
    public UserResponse login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .build())
                .orElse(null);
    }

    @Override
    public UserResponse register(UserRegistrationRequest userRegistration) {
        User user = User.builder()
                .username(userRegistration.getUsername())
                .password(userRegistration.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .build();
    }
}
