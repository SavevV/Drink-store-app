package com.example.drinkstore.service.impl;

import com.example.drinkstore.exception.SupplierNotFoundException;
import com.example.drinkstore.model.Supplier;
import com.example.drinkstore.repository.SupplierRepository;
import com.example.drinkstore.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    public final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> findAll() {
        return this.supplierRepository.findAll();
    }

    @Override
    public Supplier findById(Long id) {
        return this.supplierRepository.findById(id)
                .orElseThrow(()->new SupplierNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        this.supplierRepository.deleteById(id);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }
}
