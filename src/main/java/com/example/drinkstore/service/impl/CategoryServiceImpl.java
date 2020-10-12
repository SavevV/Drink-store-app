package com.example.drinkstore.service.impl;

import com.example.drinkstore.exception.CategoryNotFoundException;
import com.example.drinkstore.model.Category;
import com.example.drinkstore.repository.CategoryRepository;
import com.example.drinkstore.repository.DrinkRepository;
import com.example.drinkstore.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DrinkRepository drinkRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, DrinkRepository drinkRepository) {
        this.categoryRepository = categoryRepository;
        this.drinkRepository = drinkRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(id));
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category category1 = this.findById(id);
        category1.setName(category.getName());
        return this.categoryRepository.save(category1);
    }

    @Override
    public void deleteById(Long id) {
//        Category category = this.findById(id);
     this.categoryRepository.deleteById(id);
    }
}
