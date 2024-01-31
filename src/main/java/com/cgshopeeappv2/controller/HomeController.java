package com.cgshopeeappv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/home"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("content/result");
        return modelAndView;
    }
}
