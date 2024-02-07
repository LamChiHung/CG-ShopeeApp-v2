package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Seller;

public interface ISellerService {

    void save(Seller seller);

    Seller getSellerByAccount_username(String account);
}
