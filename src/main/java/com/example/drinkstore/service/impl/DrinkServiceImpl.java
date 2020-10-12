package com.example.drinkstore.service.impl;

import com.example.drinkstore.exception.DrinkNotFoundException;
import com.example.drinkstore.model.Category;
import com.example.drinkstore.model.Drink;
import com.example.drinkstore.model.Supplier;
import com.example.drinkstore.repository.DrinkRepository;
import com.example.drinkstore.service.CategoryService;
import com.example.drinkstore.service.DrinkService;
import com.example.drinkstore.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {

    public final DrinkRepository drinkRepository;
    public final CategoryService categoryService;
    public final SupplierService supplierService;

    public DrinkServiceImpl(DrinkRepository drinkRepository, CategoryService categoryService, SupplierService supplierService){
        this.drinkRepository=drinkRepository;
        this.categoryService=categoryService;
        this.supplierService = supplierService;
    }

    @Override
    public List<Drink> findAllByCategoriesId(Long id) {
        return null;
    }

    @Override
    public List<Drink> findAll() {
        return this.drinkRepository.findAll();
    }

    @Override
    public Drink findById(Long id) {
        return this.drinkRepository.findById(id)
                .orElseThrow(()->new DrinkNotFoundException(id));
    }

    @Override
    public Drink save(Drink drink, MultipartFile image) throws IOException {
        Category category = categoryService.findById(drink.getCategory().getId());
        Supplier supplier = supplierService.findById(drink.getSupplier().getId());
        drink.setSupplier(supplier);
        drink.setCategory(category);
        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            drink.setBase64Image(base64Image);
        }
        return this.drinkRepository.save(drink);

    }

    @Override
    public Drink update(Long id, Drink drink, MultipartFile image) throws IOException {
        Drink updateDrink = this.findById(id);
        Category category = this.categoryService.findById(drink.getCategory().getId());
        Supplier supplier = this.supplierService.findById(drink.getSupplier().getId());
        updateDrink.setSupplier(supplier);
        updateDrink.setCategory(category);
        updateDrink.setName(drink.getName());
        updateDrink.setPrice(drink.getPrice());
        updateDrink.setQuantity(drink.getQuantity());
        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            drink.setBase64Image(base64Image);
        }
        return this.drinkRepository.save(updateDrink);

    }

    @Override
    public void deleteById(Long id) {
        this.drinkRepository.deleteById(id);
    }
}
