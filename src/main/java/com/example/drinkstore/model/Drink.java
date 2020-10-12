package com.example.drinkstore.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public String name;

    public Integer quantity;

    public Float price;

    @Column(name = "image")
    public String base64Image;

    @ManyToOne
    @NotNull
    public Category category;

    @ManyToOne
    @NotNull
    public Supplier supplier;

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public Supplier getSupplier(){
        return supplier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
