package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Category;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.service.ICategoryService;
import com.cgshopeeappv2.service.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;

    @RequestMapping("")
    public ModelAndView search(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "category", defaultValue = "") String category,
            @RequestParam(name = "from", defaultValue = "0") Integer from,
            @RequestParam(name = "to", defaultValue = "2000000000") Integer to,
            @RequestParam(name = "star", defaultValue = "0") Integer star,
            @RequestParam(name = "sort", defaultValue = "") String sort,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            HttpServletRequest request
    ) {

        List <Category> categoryList = categoryService.getAll();
        List <String> categoryString = Arrays.stream(category.split(",")).toList();
        if (category.isEmpty()) {
            category = "1,2,3,4,5,6,7,8,9,10";
        }
        Page <Product> products = productService.search(keyword, category, from, to, star, sort, page);
        ModelAndView modelAndView = new ModelAndView("content/result");
        modelAndView.addObject("products", products);
        modelAndView.addObject("categoryList", categoryList);


        modelAndView.addObject("category", categoryString);
        modelAndView.addObject("from", from);
        modelAndView.addObject("to", to);
        modelAndView.addObject("star", star);
        modelAndView.addObject("sort", sort);

//        Lấy đường dẫn hiện tại để set vào url chuyển page
        String url = request.getRequestURI();
        modelAndView.addObject("url", url);
        modelAndView.addObject("action", "search");
        modelAndView.addObject("keyword", keyword);
        return modelAndView;
    }
}