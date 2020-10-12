package com.example.drinkstore.service;

import com.example.drinkstore.dto.ChargeRequest;
import com.example.drinkstore.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart findActiveShoppingCartByUsername(String userId);
    List<ShoppingCart> findAllByUsername(String userId);
    ShoppingCart createShoppingCart(String userId);
    ShoppingCart getActiveShoppingCartOrCreateNew(String userId);
    ShoppingCart addDrinkToShoppingCart(String userId, Long drinkId);
    ShoppingCart removeDrinkFromShoppingCart(String userId, Long drinkId);
    ShoppingCart cancelActiveShoppingCart(String userId);
    ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest);
}
