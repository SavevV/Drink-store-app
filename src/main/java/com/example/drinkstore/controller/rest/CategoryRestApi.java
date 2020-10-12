package com.example.drinkstore.controller.rest;

import com.example.drinkstore.model.Category;
import com.example.drinkstore.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestApi {

    public final CategoryService categoryService;

    public CategoryRestApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll(){
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return this.categoryService.findById(id);
    }

    @PostMapping
    public Category save(Category category){
        return this.categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, Category category){
        return this.categoryService.update(id,category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        this.categoryService.deleteById(id);
    }
}
