package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.dto.RegisterObject;
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
            return "redirect:/logup";
        } else {
            if (! registerObject.getPassword().equals(registerObject.getCorrectPassword())) {
                redirectAttributes.addFlashAttribute("message", "Xác nhận password không hợp lệ.");
                return "redirect:/logup";
            } else {
                redirectAttributes.addFlashAttribute("message", "Đăng ký thành công, vui lòng đăng nhập để tiếp tục.");
                return "redirect:/login";
            }
        }
    }
}
