package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.entity.User;

import java.util.List;

public interface ICartItemService {
    void save(User user, Integer productId);

    List <CartItem> getByUser(User user);
}
