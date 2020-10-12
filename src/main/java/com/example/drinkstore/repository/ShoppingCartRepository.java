package com.example.drinkstore.repository;

import com.example.drinkstore.model.ShoppingCart;
import com.example.drinkstore.model.enumerations.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserUsernameAndCartStatus(String username, CartStatus status);
    List<ShoppingCart> findAllByUserUsername(String username);
    boolean existsByUserUsernameAndCartStatus(String username, CartStatus status);
}
