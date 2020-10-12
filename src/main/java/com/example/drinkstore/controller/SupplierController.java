package com.example.drinkstore.controller;

import com.example.drinkstore.model.Category;
import com.example.drinkstore.model.Supplier;
import com.example.drinkstore.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String listSuppliers(Model model){
        List<Supplier> suppliers = this.supplierService.findAll();
        model.addAttribute("suppliers", suppliers);
        return "suppliers";
    }

    @PostMapping
    public String save(@Valid Supplier supplier){
        try {
            if (supplier.getId() == null) {
                this.supplierService.save(supplier);
            }
            return "redirect:/suppliers";
        } catch (RuntimeException ex) {
            return "redirect:/suppliers/add-new?error=" + ex.getLocalizedMessage();
        }
    }

    @GetMapping("/add-new")
    public String addSupplierForm(Model model){
        model.addAttribute("supplier",new Supplier());
        return "add-supplier";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        this.supplierService.deleteById(id);
        return "redirect:/suppliers";
    }
}
