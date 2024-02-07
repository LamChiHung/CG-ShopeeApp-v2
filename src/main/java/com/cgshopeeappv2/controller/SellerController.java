package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.repository.ISellerRepo;
import com.cgshopeeappv2.service.ISellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class SellerController {
    @Autowired
    private ISellerService iSellerService;
    @Autowired
    private ISellerRepo iSellerRepo;

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

    @RequestMapping("/statistics")
    public ModelAndView statistics() {
        ModelAndView modelAndView = new ModelAndView("content/seller-statistics");
        return modelAndView;
    }

    Account account = new Account("lamchihung24@gmail.com", "");

    @RequestMapping("/information")
    public ModelAndView information(Model model) {
        model.addAttribute("seller", iSellerService.getSellerByAccount_username(account.getUsername()));
        ModelAndView modelAndView = new ModelAndView("content/seller-information");
        return modelAndView;
    }

    @RequestMapping("/bill")
    public ModelAndView bill() {
        ModelAndView modelAndView = new ModelAndView("content/bill-management");
        return modelAndView;
    }

    @RequestMapping("/voucher")
    public ModelAndView voucher() {
        ModelAndView modelAndView = new ModelAndView("content/voucher-management");
        return modelAndView;
    }

    @RequestMapping("/history")
    public ModelAndView history() {
        ModelAndView modelAndView = new ModelAndView("content/seller-history");
        return modelAndView;
    }

    @PostMapping("/change-information")
    public String changeInfo(@Valid @ModelAttribute("seller") Seller seller, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "redirect:/information";
        }
        iSellerService.save(seller);
        System.out.println(seller.toString());
        return "redirect:/information";
    }
}
