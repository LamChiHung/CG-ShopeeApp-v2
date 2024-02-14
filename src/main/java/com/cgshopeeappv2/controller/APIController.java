package com.cgshopeeappv2.controller;


import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.service.ICartItemService;
import com.cgshopeeappv2.service.IProductService;
import com.cgshopeeappv2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    IProductService productService;
    @Autowired
    ICartItemService cartItemService;
    @Autowired
    IUserService userService;


    public ResponseEntity <Void> redirect() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("https://api.nextbillion.io/distancematrix/json?origins=34.05456317,-118.31528428&destinations=33.96763110,-118.23215346&mode=car&key=b98e9dd2f9414231bae19340b76feff0&avoid=highway"))
                .build();
    }

    @GetMapping()
    public ResponseEntity <Object> getEmployees() {
        final String uri = "https://api.nextbillion.io/distancematrix/json?origins=34.05456317,-118.31528428&destinations=33.96763110,-118.23215346&mode=car&key=b98e9dd2f9414231bae19340b76feff0&avoid=highway";

        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        System.out.println(result.toString());
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity <Product> getProduct(@PathVariable("id") Integer id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/cart/add/{id}")
    public ResponseEntity <List <CartItem>> addItem(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal Account account
    ) {
        User user = userService.getUserByAccount(account.getUsername());
        cartItemService.save(user, id);
        List <CartItem> cartItems = cartItemService.getByUser(user);
        return ResponseEntity.ok().body(cartItems);
    }

    @GetMapping("/cart")
    public ResponseEntity <List <CartItem>> showCartItem(
            @AuthenticationPrincipal Account account
    ) {
        User user = userService.getUserByAccount(account.getUsername());
        List <CartItem> cartItems = cartItemService.getByUser(user);
        return ResponseEntity.ok().body(cartItems);
    }

    @GetMapping("/cart/delete/{id}")
    public ResponseEntity <List <CartItem>> deleteItem(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal Account account
    ) {
        User user = userService.getUserByAccount(account.getUsername());
        cartItemService.delete(user, id);
        List <CartItem> cartItems = cartItemService.getByUser(user);
        return ResponseEntity.ok().body(cartItems);
    }
}
