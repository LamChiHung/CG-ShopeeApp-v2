package com.cgshopeeappv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/info"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("content/seller-information");
        return modelAndView;
    }
}

