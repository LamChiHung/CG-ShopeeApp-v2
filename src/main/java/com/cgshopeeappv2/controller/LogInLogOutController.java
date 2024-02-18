package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.dto.RegisterObject;
import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Wallet;
import com.cgshopeeappv2.repository.WalletRepo;
import com.cgshopeeappv2.service.IMailService;
import com.cgshopeeappv2.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class LogInLogOutController {
    @Autowired
    private IRegisterService registerService;
    @Autowired
    private IMailService mailService;
    @Autowired
    private WalletRepo walletRepo;


    @GetMapping("/login")
    public ModelAndView logIn(@RequestParam(value = "message", defaultValue = "") String message) {
        ModelAndView modelAndView = new ModelAndView("content/signin-form");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("content/signup-form");
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("register") RegisterObject registerObject,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Dữ liệu không hợp lệ.");
            return "redirect:/register";
        } else {
            if (! registerObject.getPassword().equals(registerObject.getCorrectPassword())) {
                redirectAttributes.addFlashAttribute("message", "Xác nhận password không hợp lệ.");
                return "redirect:/register";
            } else {
                Account account = registerService.findById(registerObject.getUsername());
                if (account == null) {
                    Account account1 = registerService.createNewUserAccount(registerObject.getUsername(), registerObject.getPassword());
                    mailService.verifyAccount(account1);
                    Wallet wallet = new Wallet(account1.getUsername(), 1000000);
                    walletRepo.saveAndFlush(wallet);
                    redirectAttributes.addFlashAttribute("message", "Đăng ký thành công, mã xác thực tài khoản đã được gửi đến email đăng ký.");
                    return "redirect:/login";
                } else {
                    redirectAttributes.addFlashAttribute("message", "Tài khoản đã được sử dụng.");
                    return "redirect:/register";
                }

            }
        }
    }
}
