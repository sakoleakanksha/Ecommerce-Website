package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;

@Service
public class Cart_service {

    @Autowired
    private CartRepository cartRepo;

    // ADD PRODUCT TO CART
    public Cart addToCart(Cart cart) {

        // Check if same product already exists in user's cart
        User user = cart.getUser();
        Product product = cart.getProduct();

        Cart existingItem = cartRepo.findByUserAndProduct(user, product);

        if (existingItem != null) {
            // Update quantity
            existingItem.setQuantity(existingItem.getQuantity() + cart.getQuantity());
            return cartRepo.save(existingItem);
        }

        return cartRepo.save(cart);
    }

    // GET CART BY USER
    public List<Cart> getCartByUser(User user) {
        return cartRepo.findByUser(user);
    }

    // REMOVE A SINGLE ITEM
    public void removeFromCart(int cartId) {
        cartRepo.deleteById(cartId);
    }

    // CLEAR ENTIRE CART OF USER
    public void clearCart(User user) {
        List<Cart> userCart = cartRepo.findByUser(user);
        cartRepo.deleteAll(userCart);
    }

    // UPDATE QUANTITY
    public Cart updateQuantity(int cartId, int quantity) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found: " + cartId));

        cart.setQuantity(quantity);
        return cartRepo.save(cart);
    }
}
