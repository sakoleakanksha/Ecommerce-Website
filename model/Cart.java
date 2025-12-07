package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="u_id", referencedColumnName = "u_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="p_id", referencedColumnName = "p_id")
    private Product product;

    // Getters & Setters
    public int getC_id() { return c_id; }
    public void setC_id(int c_id) { this.c_id = c_id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
