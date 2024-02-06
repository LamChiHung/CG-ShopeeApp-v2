package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.service.NewAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    NewAccount newAccount;

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("content/home");
        return modelAndView;
    }

    @RequestMapping(value = {"/discovery"})
    public ModelAndView discovery() {
        ModelAndView modelAndView = new ModelAndView("content/result");
        return modelAndView;
    }
}
