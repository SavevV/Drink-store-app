package com.example.drinkstore.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupplierNotFoundException extends RuntimeException {

    public SupplierNotFoundException(Long id){
        super(String.format("Supplier with %d id, id not found.",id));
    }
}
