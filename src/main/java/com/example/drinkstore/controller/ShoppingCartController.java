package com.example.drinkstore.controller;

import com.example.drinkstore.model.ShoppingCart;
import com.example.drinkstore.service.AuthService;
import com.example.drinkstore.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @PostMapping("{drinkId}/add-drink")
    public String addDrinkToShoppingCart(@PathVariable Long drinkId){
        try{
            ShoppingCart shoppingCart = this.shoppingCartService.addDrinkToShoppingCart(authService.getCurrentUserId(),drinkId);
        }catch (RuntimeException ex){
            return "redirect:/drinks?error="+ ex.getLocalizedMessage();
        }
        return "redirect:/drinks";
    }

    @PostMapping("{drinkId}/remove-drink")
    public String removeDrinkFromShoppingCart(@PathVariable Long drinkId){
        ShoppingCart shoppingCart = this.shoppingCartService.removeDrinkFromShoppingCart(this.authService.getCurrentUserId(),drinkId);
        return "redirect:/drinks";
    }
}
