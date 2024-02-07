package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Seller;

public interface ISellerService {
    Seller getByAccountUsername(String username);
}
