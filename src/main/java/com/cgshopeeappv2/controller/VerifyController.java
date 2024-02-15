package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verify")
public class VerifyController {
    @Autowired
    private IRegisterService registerService;

    @RequestMapping("")
    public String verify(
            @RequestParam("email") String email,
            @RequestParam("code") String code
    ) {
        registerService.enableAccount(email, code);
        return "redirect:/login";
    }
}
