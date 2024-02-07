package com.cgshopeeappv2.service.impl;

import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.repository.ISellerRepo;
import com.cgshopeeappv2.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService implements ISellerService {
    @Autowired
    private ISellerRepo iSellerRepo;



    @Override
    public void save(Seller seller) {
        this.iSellerRepo.save(seller);
    }

    @Override
    public Seller getSellerByAccount_username(String account_name) {
        return iSellerRepo.findSellerByAccount_Username(account_name);
    }
}
