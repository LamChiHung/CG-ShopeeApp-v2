package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.repository.CartItemRepo;
import com.cgshopeeappv2.repository.ProductRepo;
import com.cgshopeeappv2.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    ProductRepo productRepo;

    @Override
    public void save(User user, Integer productId) {
        CartItem cartItem = cartItemRepo.findByUserIdAndProductId(user.getId(), productId);
        if (cartItem == null) {
            CartItem cartItem1 = new CartItem();
            cartItem1.setQuantity(1);
            cartItem1.setUser(user);
            cartItem1.setProduct(productRepo.findById(productId).orElse(null));
            cartItemRepo.saveAndFlush(cartItem1);
        }
    }

    @Override
    public List <CartItem> getByUser(User user) {
        return cartItemRepo.findAllByUserId(user.getId());
    }
}
