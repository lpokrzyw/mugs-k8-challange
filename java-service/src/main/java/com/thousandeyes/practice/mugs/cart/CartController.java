package com.thousandeyes.practice.mugs.cart;

import com.thousandeyes.practice.mugs.mugs.Mug;
import com.thousandeyes.practice.mugs.mugs.MugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
    public void processOrder() {
        final String url = "http://node-service-svc.node-prod.svc.cluster.local:80";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cartTotal", String.valueOf(retrieveCart(CART_ID).getCartTotal()));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestBody, String.class);

        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
        if (statusCode == HttpStatus.ACCEPTED) {
            System.out.println(responseEntity.getBody());
        }
    }
}
