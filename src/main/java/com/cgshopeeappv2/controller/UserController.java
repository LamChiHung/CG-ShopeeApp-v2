package com.cgshopeeappv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/information")
    public ModelAndView information() {
        ModelAndView modelAndView = new ModelAndView("content/user-information");
        return modelAndView;
    }

    @RequestMapping("/order")
    public ModelAndView order() {
        ModelAndView modelAndView = new ModelAndView("content/order-user");
        return modelAndView;
    }

    @RequestMapping("/address")
    public ModelAndView address() {
        ModelAndView modelAndView = new ModelAndView("content/address-user");
        return modelAndView;
    }

    @RequestMapping("/notify")
    public ModelAndView announcement() {
        ModelAndView modelAndView = new ModelAndView("content/notify-user");
        return modelAndView;
    }

    @RequestMapping("/wallet")
    public ModelAndView wallet() {
        ModelAndView modelAndView = new ModelAndView("content/wallet");
        return modelAndView;
    }
}
