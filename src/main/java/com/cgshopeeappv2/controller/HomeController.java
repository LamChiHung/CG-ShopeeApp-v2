package com.cgshopeeappv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = {"/"})
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

