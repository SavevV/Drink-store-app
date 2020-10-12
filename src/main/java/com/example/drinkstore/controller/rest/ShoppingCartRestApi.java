package com.example.drinkstore.controller.rest;

import com.example.drinkstore.model.ShoppingCart;
import com.example.drinkstore.service.AuthService;
import com.example.drinkstore.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

@RestController("/api/shopping-carts")
public class ShoppingCartRestApi {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartRestApi(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @PostMapping
    public ShoppingCart createShoppingCart(){
        return this.shoppingCartService.createShoppingCart(this.authService.getCurrentUserId());
    }

    @PatchMapping("/{drinkId}/drinks")
    ShoppingCart addDrinkToShoppingCart(@PathVariable Long drinkId){
        return this.shoppingCartService.addDrinkToShoppingCart(
                this.authService.getCurrentUserId(),
                drinkId
        );
    }
    @DeleteMapping("/{drinkId}/drinks")
    ShoppingCart removeProductFromShoppingCart(@PathVariable Long drinkId){
        return this.shoppingCartService.removeDrinkFromShoppingCart(
                this.authService.getCurrentUserId(),
                drinkId
        );
    }
    @DeleteMapping
    ShoppingCart cancelActiveShoppingCart(){
        return this.shoppingCartService.cancelActiveShoppingCart(this.authService.getCurrentUserId());
    }

//    @PostMapping("/checkout")
//    ShoppingCart checkoutSShoppingCart(){
//        return this.shoppingCartService.checkoutShoppingCart(this.authService.getCurrentUserId());
//    }
}
