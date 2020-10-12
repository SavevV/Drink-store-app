package com.example.drinkstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @OneToMany
    @JsonIgnore
    public List<Drink> drinks;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}
