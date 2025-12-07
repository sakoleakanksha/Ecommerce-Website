package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.service.Cart_service;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")    // for frontend access
public class CartController {

    @Autowired
    private Cart_service cartService;

    // ADD TO CART
    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    // GET CART OF A USER
    @GetMapping("/user/{userId}")
    public List<Cart> getUserCart(@PathVariable int userId) {
        User user = new User();
        user.setU_id(userId);

        return cartService.getCartByUser(user);
    }

    // UPDATE QUANTITY
    @PutMapping("/update/{cartId}/{quantity}")
    public Cart updateQuantity(@PathVariable int cartId, @PathVariable int quantity) {
        return cartService.updateQuantity(cartId, quantity);
    }

    // REMOVE SINGLE PRODUCT FROM CART
    @DeleteMapping("/remove/{cartId}")
    public String removeItem(@PathVariable int cartId) {
        cartService.removeFromCart(cartId);
        return "Item removed successfully";
    }

    // CLEAR ENTIRE CART
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable int userId) {
        User user = new User();
        user.setU_id(userId);

        cartService.clearCart(user);
        return "Cart cleared successfully";
    }
}
