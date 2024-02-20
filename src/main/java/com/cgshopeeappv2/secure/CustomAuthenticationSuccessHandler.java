package com.cgshopeeappv2.secure;

import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.repository.AccountRepo;
import com.cgshopeeappv2.repository.SellerRepo;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.service.IMailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private IMailService mailService;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = userRepo.getUserByAccount_Username(authentication.getName());
        Seller seller = sellerRepo.findByAccount_Username(authentication.getName());
        if (seller != null) {
            session.setAttribute("seller", seller);
        }
        session.setAttribute("user", user);
        // Chuyển hướng sau khi đăng nhập thành công
        response.sendRedirect("/home");
    }
}
