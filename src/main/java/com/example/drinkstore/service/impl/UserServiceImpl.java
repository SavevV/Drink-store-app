package com.example.drinkstore.service.impl;

import com.example.drinkstore.exception.UserNotFoundException;
import com.example.drinkstore.model.User;
import com.example.drinkstore.repository.UserRepository;
import com.example.drinkstore.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }
}
