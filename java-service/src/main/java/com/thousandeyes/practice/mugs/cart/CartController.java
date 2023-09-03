package com.thousandeyes.practice.mugs.cart;

import com.thousandeyes.practice.mugs.mugs.Mug;
import com.thousandeyes.practice.mugs.mugs.MugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final int CART_ID = 1; // hard coded for now

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MugRepository mugRepository;

    public Mug retrieveMug(int id) {
        return mugRepository.findMugById(id);
    }

    public Cart retrieveCart(int id) {
        return cartRepository.findCartById(id);
    }

    public void updateCartTotal(double cartTotal) {
        Cart myCart = retrieveCart(CART_ID);
        myCart.cartTotal = cartTotal;
        cartRepository.save(myCart);
    }

    @PostMapping(path = "/cart/addToCart")
    public Void addToCart(Model model, @RequestParam(value = "code", defaultValue = "") String code) {

        model.addAttribute("productCode", code);
        double mugPrice = retrieveMug(Integer.valueOf(code)).getPrice();
        System.out.println(mugPrice);
        updateCartTotal(retrieveCart(CART_ID).getCartTotal() + mugPrice);
        return null;
    }

    @PostMapping(path = "/checkout")
    public Void processOrder() {
        updateCartTotal(0);
        return null;
    }
}
