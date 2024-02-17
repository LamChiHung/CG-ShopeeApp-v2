package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.repository.CartItemRepo;
import com.cgshopeeappv2.service.IBillService;
import com.cgshopeeappv2.service.ICartItemService;
import com.cgshopeeappv2.service.IUserService;
import com.cgshopeeappv2.service.implement.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    IBillService billService;
    @Autowired
    ICartItemService cartItemService;
    @Autowired
    IUserService userService;
    @Autowired
    CartItemRepo cartItemRepo;

    @RequestMapping("")
    public String payment(
            HttpServletRequest request,
            @AuthenticationPrincipal Account account,
            RedirectAttributes redirectAttributes
    ) {
        List <CartItem> cartItems = cartItemService.getByUser(userService.getUserByAccount(account.getUsername()));
        for (CartItem cartItem : cartItems) {
            String quantity = request.getParameter(cartItem.getId().toString());
            cartItem.setQuantity(Integer.parseInt(quantity));
            cartItemRepo.saveAndFlush(cartItem);
        }
        String message = paymentService.payment(account);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/home";
    }
}
