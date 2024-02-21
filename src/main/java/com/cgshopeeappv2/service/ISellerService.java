package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISellerService {
    void save(Seller seller);
    Seller getSellerByAccount_username(String account);
    Seller getByAccountUsername(String username);

}
