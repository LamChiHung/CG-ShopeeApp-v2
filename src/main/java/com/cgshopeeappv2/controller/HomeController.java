package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Category;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.service.implement.CategoryService;
import com.cgshopeeappv2.service.implement.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView home(
            @CookieValue(name = "search", defaultValue = "") String search,
            HttpServletResponse response
    ) {
//        Lấy cookie lịch sử tìm kiếm để hiển thị sản phẩm recommend
        String trend1 = "";
        String trend2 = "";
        if (search.isEmpty()||search.equals(".")) {

            Cookie cookie = new Cookie("search", "");
            cookie.isHttpOnly();
            cookie.setMaxAge(2 * 365 * 24 * 60 * 60);
            response.addCookie(cookie);
        } else {
            search = search.replaceAll("-", " ");
            String[] searchs = search.split("\\.");
            if (searchs.length != 0) {
                trend1 = searchs[0];
                trend2 = searchs[1];
            }
        }
//        kết thúc
        Page <Product> products = productService.findTrendByName(trend1, trend2, PageRequest.of(0, 12));
        ModelAndView modelAndView = new ModelAndView("content/home");
        List <Category> categories = categoryService.getAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @RequestMapping(value = {"/discovery"})
    public ModelAndView discovery(
            @CookieValue(name = "search", defaultValue = "") String search,
            HttpServletResponse response,
            @PageableDefault(10) Pageable pageable,
            HttpServletRequest request,
            @RequestParam(name = "category", defaultValue = "") String category,
            @RequestParam(name = "from", defaultValue = "0") Integer from,
            @RequestParam(name = "to", defaultValue = "2000000000") Integer to
    ) {
        String trend1;
        String trend2;

        if (search.isEmpty()) {
            trend1 = "";
            trend2 = "";
        } else {
            search = search.replaceAll("-", " ");
            String[] searchs = search.split("\\.");
            trend1 = searchs[0];
            trend2 = searchs[1];
        }
        List <Category> categoryList = categoryService.getAll();
        Page <Product> products = productService.findTrendByName(trend1, trend2, pageable);
        ModelAndView modelAndView = new ModelAndView("content/result");
        modelAndView.addObject("products", products);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("page", pageable);
        modelAndView.addObject("action", "discovery");
        modelAndView.addObject("category", category);
        modelAndView.addObject("to", to);
        modelAndView.addObject("from", from);

//        Lấy đường dẫn hiện tại để set vào url chuyển page
        String url = request.getRequestURI();
        modelAndView.addObject("url", url);
        return modelAndView;
    }
}

