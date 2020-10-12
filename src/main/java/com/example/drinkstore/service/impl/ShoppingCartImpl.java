package com.example.drinkstore.service.impl;

import com.example.drinkstore.dto.ChargeRequest;
import com.example.drinkstore.exception.*;
import com.example.drinkstore.model.Drink;
import com.example.drinkstore.model.ShoppingCart;
import com.example.drinkstore.model.User;
import com.example.drinkstore.model.enumerations.CartStatus;
import com.example.drinkstore.repository.ShoppingCartRepository;
import com.example.drinkstore.service.DrinkService;
import com.example.drinkstore.service.PaymentService;
import com.example.drinkstore.service.ShoppingCartService;
import com.example.drinkstore.service.UserService;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final DrinkService drinkService;
    private final PaymentService paymentService;

    public ShoppingCartImpl(ShoppingCartRepository shoppingCartRepository,
                            UserService userService,
                            DrinkService drinkService, PaymentService paymentService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.drinkService = drinkService;
        this.paymentService = paymentService;
    }

    @Override
    public ShoppingCart findActiveShoppingCartByUsername(String userId) {
        return this.shoppingCartRepository.findByUserUsernameAndCartStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));
    }

    @Override
    public List<ShoppingCart> findAllByUsername(String userId) {
        return this.shoppingCartRepository.findAllByUserUsername(userId);
    }

    @Override
    public ShoppingCart createShoppingCart(String userId) {
        User user = this.userService.findById(userId);
        if (this.shoppingCartRepository.existsByUserUsernameAndCartStatus(
                user.getUsername(),
                CartStatus.CREATED
        )) {
            throw new ShoppingCartIsAlreadyCreated(userId);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart addDrinkToShoppingCart(String userId, Long drinkId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCartOrCreateNew(userId);
        Drink drink = this.drinkService.findById(drinkId);
        for (Drink d : shoppingCart.getDrink()) {
            if (d.getId().equals(drinkId)) {
                throw new DrinkIsAlreadyInShoppingCartException(drink.getName());
            }
        }
        shoppingCart.getDrink().add(drink);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeDrinkFromShoppingCart(String userId, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCartOrCreateNew(userId);
        shoppingCart.setDrink(
                shoppingCart.getDrink()
                        .stream()
                        .filter(product -> !product.getId().equals(productId))
                        .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getActiveShoppingCartOrCreateNew(String userId) {
        return this.shoppingCartRepository
                .findByUserUsernameAndCartStatus(userId, CartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    User user = this.userService.findById(userId);
                    shoppingCart.setUser(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart cancelActiveShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndCartStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));
        shoppingCart.setCartStatus(CartStatus.CANCELED);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndCartStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));

        List<Drink> products = shoppingCart.getDrink();
        float price = 0;

        for (Drink product : products) {
            if (product.getQuantity() <= 0) {
                throw new ProductOutOfStockException(product.getName());
            }
            product.setQuantity(product.getQuantity() - 1);
            price += product.getPrice();
        }
        Charge charge = null;
        try {
            charge = this.paymentService.pay(chargeRequest);
        } catch (CardException | APIException | AuthenticationException | APIConnectionException | InvalidRequestException e) {
            throw new TransactionFailedException(userId, e.getMessage());
        }

        shoppingCart.setDrink(products);
        shoppingCart.setCartStatus(CartStatus.FINISHED);
        return this.shoppingCartRepository.save(shoppingCart);
    }

}
