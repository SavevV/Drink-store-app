package com.example.drinkstore.controller.rest;

import com.example.drinkstore.model.Supplier;
import com.example.drinkstore.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierRestApi {

    public final SupplierService supplierService;

    public SupplierRestApi(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> findAll(){
        return this.supplierService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.supplierService.deleteById(id);
    }

    @PostMapping
    public Supplier save(Supplier supplier){
        return this.supplierService.save(supplier);
    }
}
