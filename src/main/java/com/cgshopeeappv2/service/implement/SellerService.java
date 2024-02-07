package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.repository.SellerRepo;
import com.cgshopeeappv2.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService implements ISellerService {
    @Autowired
    SellerRepo sellerRepo;

    @Override
    public Seller getByAccountUsername(String username) {
        return sellerRepo.findByAccount_Username(username);
    }
}
