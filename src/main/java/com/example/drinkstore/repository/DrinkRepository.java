package com.example.drinkstore.repository;

import com.example.drinkstore.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink,Long> {
}
