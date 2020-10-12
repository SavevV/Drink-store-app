package com.example.drinkstore.service;

import com.example.drinkstore.model.Drink;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DrinkService {

    List<Drink> findAllByCategoriesId(Long id);
    List<Drink> findAll();
    Drink findById(Long id);
    Drink save(Drink drink, MultipartFile image) throws IOException;
    Drink update(Long id, Drink drink, MultipartFile image) throws IOException;
    void deleteById(Long id);
}
