package com.example.drinkstore.controller;

import com.example.drinkstore.dto.ChargeRequest;
import com.example.drinkstore.model.Drink;
import com.example.drinkstore.model.ShoppingCart;
import com.example.drinkstore.service.AuthService;
import com.example.drinkstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Value("${STRIPE_P_KEY}")
    public String p_key;

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public PaymentController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping("/charge")
    public String getCheckoutPage(Model model){
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.findActiveShoppingCartByUsername(this.authService.getCurrentUserId());
            model.addAttribute("shoppingCart", shoppingCart);
            model.addAttribute("currency","eur");
            model.addAttribute("amount", (int)(shoppingCart.getDrink().stream().mapToDouble(Drink::getPrice).sum()*100));
            model.addAttribute("stripePublicKey",this.p_key);
            return "checkout";
        }catch (RuntimeException ex){
            return "redirect:/drinks?error = "+ ex.getLocalizedMessage();
        }
    }

    @PostMapping("/charge")
    public String checkout(ChargeRequest chargeRequest, Model model){
        try{
            ShoppingCart shoppingCart = this.shoppingCartService.checkoutShoppingCart(this.authService.getCurrentUserId(),chargeRequest);
            return "redirect:/drinks?message=Successful Payment";
        }catch (RuntimeException ex){
            return "redirect:/payments/charge?error="+ ex.getLocalizedMessage();
        }
    }
}
