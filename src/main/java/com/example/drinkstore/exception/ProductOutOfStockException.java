package com.example.drinkstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductOutOfStockException extends RuntimeException{

    public ProductOutOfStockException(String DrinkName){
        super(String.format("Drink with name %d is out of stock!",DrinkName));
    }
}
