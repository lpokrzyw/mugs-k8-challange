package com.thousandeyes.practice.mugs.cart;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartById(int id);
}
