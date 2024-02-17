package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Category;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.service.ICategoryService;
import com.cgshopeeappv2.service.IProductService;
import com.cgshopeeappv2.service.ISellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private ISellerService iSellerService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ISellerService sellerService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/product")
    public ModelAndView product(@AuthenticationPrincipal Account account) {
        String username = account.getUsername();
        Seller seller = sellerService.getByAccountUsername(username);
        List <Product> products = productService.getAllBySellerId(seller.getId());
        ModelAndView modelAndView = new ModelAndView("content/product-management");
        modelAndView.addObject("products", products);
        Product product = new Product();
        modelAndView.addObject("product", product);
        List <Category> categories = categoryService.getAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/product")
    public String product(
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal Account account,
            @Validated @ModelAttribute("product") Product product, BindingResult bindingResult) {
        productService.save(product, account);
        redirectAttributes.addFlashAttribute("message", "Lưu sản phẩm thành công");
        return "redirect:/seller/product";
    }

    @GetMapping("/delete/product/{id}")
    public String delete(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal Account account,
            RedirectAttributes redirectAttributes
    ) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công");
        return "redirect:/seller/product";
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

//    Account account = new Account("lamchihung24@gmail.com","");

    @RequestMapping("/information")
    public ModelAndView information(Model model, @AuthenticationPrincipal Account account) {
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
