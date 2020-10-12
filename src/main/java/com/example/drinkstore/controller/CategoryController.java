package com.example.drinkstore.controller;

import com.example.drinkstore.model.Category;
import com.example.drinkstore.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model){
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @PostMapping
    public String save(@Valid Category category){
        try {
            if (category.getId() == null) {
                this.categoryService.save(category);
            } else {
                this.categoryService.update(category.getId(), category);
            }
            return "redirect:/categories";
        } catch (RuntimeException ex) {
            return "redirect:/categories/add-new?error=" + ex.getLocalizedMessage();
        }
    }

    @GetMapping("/add-new")
    public String addCategoryForm(Model model){
        model.addAttribute("category",new Category());
        return "add-category";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        this.categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
