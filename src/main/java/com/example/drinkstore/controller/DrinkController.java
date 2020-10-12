package com.example.drinkstore.controller;

import com.example.drinkstore.model.Category;
import com.example.drinkstore.model.Drink;
import com.example.drinkstore.model.Supplier;
import com.example.drinkstore.service.CategoryService;
import com.example.drinkstore.service.DrinkService;
import com.example.drinkstore.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    public DrinkController(DrinkService drinkService, CategoryService categoryService, SupplierService supplierService) {
        this.drinkService = drinkService;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String listDrinks(Model model){
        List<Drink> drinks = this.drinkService.findAll();
        model.addAttribute("drinks",drinks);
        return "drinks";
    }

    @PostMapping
    public String saveOrUpdate(
            Model model,
            @Valid Drink drink,
            BindingResult bindingResult,
            @RequestParam MultipartFile image) throws IOException{
        if (bindingResult.hasErrors()) {
            List<Category> categories = this.categoryService.findAll();
            List<Supplier> suppliers = this.supplierService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("suppliers",suppliers);
            return "add-drink";
        }

        try {
            if (drink.getId() == null) {
                this.drinkService.save(drink, image);
            } else {
                this.drinkService.update(drink.getId(), drink, image);
            }
            return "redirect:/drinks";
        } catch (RuntimeException ex) {
            return "redirect:/drinks/add-new?error=" + ex.getLocalizedMessage();
        }
    }

    @GetMapping("/add-new")
    public String addNewDrink(Model model){
        List<Category> categories = this.categoryService.findAll();
        List<Supplier> suppliers = this.supplierService.findAll();
        model.addAttribute("drink",new Drink());
        model.addAttribute("categories",categories);
        model.addAttribute("suppliers",suppliers);
        return "add-drink";
    }

    @GetMapping("/{id}/edit")
    public String editDrinkPage(@PathVariable Long id,Model model){
        try{
            Drink drink = this.drinkService.findById(id);
            Supplier supplier = this.supplierService.findById(id);
            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("drink",drink);
            model.addAttribute("categories",categories);
            model.addAttribute("suppliers",supplier);
            return "add-drink";
        }catch (RuntimeException ex){
            return "redirect:/drinks?error="+ ex.getLocalizedMessage();
        }
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        this.drinkService.deleteById(id);
        return "redirect:/drinks";
    }

    @PostMapping("/{id}/delete")
    public String deleteDrinkWithPost(@PathVariable Long id) {
//        try {
            this.drinkService.deleteById(id);
//        } catch (ProductIsAlreadyInShoppingCartException ex) {
//            return String.format("redirect:/products?error=%s", ex.getMessage());
//        }
        return "redirect:/drinks";
    }

}
