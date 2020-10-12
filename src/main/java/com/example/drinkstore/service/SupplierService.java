package com.example.drinkstore.service;

import com.example.drinkstore.model.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> findAll();
    Supplier findById(Long id);
    void deleteById(Long id);
    Supplier save(Supplier supplier);
}
