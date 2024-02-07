package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.repository.ProductRepo;
import com.cgshopeeappv2.service.IProductService;
import com.cgshopeeappv2.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestAPI {
    @Autowired
    IProductService productService;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ISellerService sellerService;

    @RequestMapping("/product")
    public List <Product> product(@AuthenticationPrincipal Account account) {
        String username = account.getUsername();
        Seller seller = sellerService.getByAccountUsername(username);
        List <Product> products = productService.getAllBySellerId(seller.getId());
        ModelAndView modelAndView = new ModelAndView("content/product-management");
        modelAndView.addObject("products", products);
        return products;
    }
}
