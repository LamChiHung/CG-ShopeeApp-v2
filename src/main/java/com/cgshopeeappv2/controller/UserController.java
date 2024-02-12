package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.User;

import com.cgshopeeappv2.entity.UserAddress;
import com.cgshopeeappv2.service.IAddressUserService;
import com.cgshopeeappv2.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IAddressUserService iAddressUserService;

    @RequestMapping("/information")
    public ModelAndView information(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("user", iUserService.getUserByAccount(account.getUsername()));
        ModelAndView modelAndView = new ModelAndView("content/user-information");
        User user = iUserService.getUserByAccount(account.getUsername());
        List<UserAddress> list = iAddressUserService.getAllById(user.getId());
        for (UserAddress x : list) {
            list.toString();
        }
        return modelAndView;
    }

    @RequestMapping("/order")
    public ModelAndView order() {
        ModelAndView modelAndView = new ModelAndView("content/order-user");
        return modelAndView;
    }

    @RequestMapping("/address")
    public ModelAndView address(@AuthenticationPrincipal Account account, Model model) {
        User user = iUserService.getUserByAccount(account.getUsername());
        List<UserAddress> list = iAddressUserService.getAllById(user.getId());
        ModelAndView modelAndView = new ModelAndView("content/address-user");
        modelAndView.addObject("address_user", user);
        modelAndView.addObject("list_address_user", list);
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

    @PostMapping("/change-information")
    public ModelAndView changeInformation(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        System.out.println(user.toString());
        iUserService.save(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
        return modelAndView;
    }

    @PostMapping("/create-address")
    public ModelAndView createAddress(@ModelAttribute UserAddress userAddress) {
        iAddressUserService.save(userAddress);
        System.out.println(userAddress);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/address");
        return modelAndView;
    }

    @PostMapping("/update-address")
    public ModelAndView updateAddress(@ModelAttribute UserAddress userAddress, BindingResult bindingResult) {
        iAddressUserService.save(userAddress);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/address");
        return modelAndView;
    }

    @PostMapping("/delete-address")
    public ModelAndView deleteAddress(@RequestParam("id") int id) {
        iAddressUserService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/address");
        return modelAndView;
    }

    @PostMapping("/change-default-address")
    public ModelAndView changeDefault(@RequestParam("id") int id, @AuthenticationPrincipal Account account, Model model) {
        User user = iUserService.getUserByAccount(account.getUsername());
        UserAddress userAddress = iAddressUserService.findById(id);
        userAddress.setDefault_address("true");
        iAddressUserService.save(userAddress);
        iAddressUserService.changeDefaultAddress(id, user.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/user/address");
        return modelAndView;
    }
}
