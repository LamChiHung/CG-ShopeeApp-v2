package com.cgshopeeappv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @RequestMapping("/product")
    public ModelAndView product() {
        ModelAndView modelAndView = new ModelAndView("content/product-management");
        return modelAndView;
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("content/seller-register-form");
        return modelAndView;
    }
}
