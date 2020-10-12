package com.example.drinkstore.service.impl;

import com.example.drinkstore.model.User;
import com.example.drinkstore.repository.UserRepository;
import com.example.drinkstore.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentuser() {
        return this.userRepository.findById("user")
                .orElseGet(()->{
                    User user = new User();
                    user.setUsername("user");
                    return this.userRepository.save(user);
                });
    }

    @Override
    public String getCurrentUserId() {
        return null;
    }
}
