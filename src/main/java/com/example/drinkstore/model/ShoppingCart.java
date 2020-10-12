package com.example.drinkstore.model;

import com.example.drinkstore.model.enumerations.CartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private LocalDateTime createDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus = CartStatus.CREATED;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "cart_drinks",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private List<Drink> drink = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Drink> getDrink() {
        return drink;
    }

    public void setDrink(List<Drink> drink) {
        this.drink = drink;
    }
}
