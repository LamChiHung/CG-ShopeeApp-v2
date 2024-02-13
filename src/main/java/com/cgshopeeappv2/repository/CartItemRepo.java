package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository <CartItem, Integer> {
    List <CartItem> findAllByUserId(Integer id);

    CartItem findByUserIdAndProductId(Integer userId, Integer productId);
}
