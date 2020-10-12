package com.example.drinkstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String id){
        super(String.format("User with usernames %s is not found!",id));
    }
}
