package com.example.drinkstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DrinkNotFoundException extends RuntimeException{

    public DrinkNotFoundException(Long id){
        super(String.format("Drink with id %d is not found!",id));
    }
}
