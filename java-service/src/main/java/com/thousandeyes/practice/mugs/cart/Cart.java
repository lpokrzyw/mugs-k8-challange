package com.thousandeyes.practice.mugs.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARTS")
public class Cart {
    @Id
    private int id;
    @Column(name = "CART_TOTAL", nullable = false)
    public double cartTotal;

    public double getCartTotal() {
        return cartTotal;
    }

    public Cart() {
    }

    public Cart(int id, double cartTotal) {
        super();
        this.id = id;
        this.cartTotal = cartTotal;
    }
}
