package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.dto.ChangePasswordDTO;
import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Bill;
import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.entity.UserAddress;
import com.cgshopeeappv2.repository.AccountRepo;
import com.cgshopeeappv2.repository.BillRepo;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.service.IBillService;
import com.cgshopeeappv2.service.ICartItemService;
import com.cgshopeeappv2.service.IUserAddressService;
import com.cgshopeeappv2.service.IUserService;
import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IUserAddressService iAddressUserService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IBillService billService;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private IAccountService iAccountService;

    @Autowired
    private Cloudinary cloudinary;

//    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @RequestMapping("/information")
    public ModelAndView information(Model model, @AuthenticationPrincipal Account account, HttpServletRequest request) {
        model.addAttribute("user", iUserService.getUserByAccount(account.getUsername()));
        ModelAndView modelAndView = new ModelAndView("content/user-information");
        User user = iUserService.getUserByAccount(account.getUsername());
        List<UserAddress> list = iAddressUserService.getAllById(user.getId());
        User user1 = (User) request.getSession().getAttribute("user");
        System.out.println(user1.getName());
        return modelAndView;
    }

    @RequestMapping("/order")
    public ModelAndView order(
            @AuthenticationPrincipal Account account
    ) {
        User user = userRepo.getUserByAccount_Username(account.getUsername());
        List<Bill> bills = billRepo.findAllByUserId(user.getId());
        ModelAndView modelAndView = new ModelAndView("content/order-user");
        modelAndView.addObject("bills", bills);
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
    public ModelAndView changeInformation(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            HttpServletRequest request

    ) {
        System.out.println(user.toString());
        iUserService.save(user);
        request.getSession().setAttribute("user", user);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
        return modelAndView;
    }

    @PostMapping("/create-address")
    public ModelAndView createAddress(@ModelAttribute UserAddress userAddress) {
        iAddressUserService.save(userAddress);
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

    @GetMapping("/cart")
    public ModelAndView cart(@AuthenticationPrincipal Account account) {
        ModelAndView modelAndView = new ModelAndView("content/cart-form");
        User user = iUserService.getUserByAccount(account.getUsername());
        List<CartItem> cartItems = cartItemService.getByUser(user);
        UserAddress userAddress = iAddressUserService.findByDefaultAddress(account);
        modelAndView.addObject("cartItems", cartItems);
        modelAndView.addObject("a", userAddress);
        List<Bill> bills = billService.createBill(account);
        Integer deliveryCharge = billService.deliveryCharge(account, bills);
        modelAndView.addObject("deliveryCharge", deliveryCharge);
        return modelAndView;
    }


    @PostMapping("/upload-image")
    public ModelAndView create(@RequestParam MultipartFile image, @AuthenticationPrincipal Account account) throws IOException {
        User user = iUserService.getUserByAccount(account.getUsername());

        Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

        String imageUrl = (String) uploadResult.get("url");

        user.setImg(imageUrl);
        iUserService.save(user);

        ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
        return modelAndView;
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword(@AuthenticationPrincipal Account account, @ModelAttribute ChangePasswordDTO changePasswordDTO, BindingResult bindingResult) {
        boolean isValidOldPW = passwordEncoder.matches(changePasswordDTO.getOldPW(), account.getPassword());
        boolean isValidNewPW = changePasswordDTO.getNewPW().equals(changePasswordDTO.getNewAgainPW());
        if (isValidOldPW && isValidNewPW) {
            account.setPassword(changePasswordDTO.getNewPW());
            accountRepo.save(account);
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
            return modelAndView;
        }
    }
}
