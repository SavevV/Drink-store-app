package com.example.drinkstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShoppingCartIsNotActiveException extends RuntimeException{
    public ShoppingCartIsNotActiveException(String userId) {
        super(String.format("There is no active shopping cart for user %s!", userId));
    }
}
