package com.example.drinkstore.service;

import com.example.drinkstore.model.User;

public interface AuthService {

    User getCurrentuser();
    String getCurrentUserId();
}
