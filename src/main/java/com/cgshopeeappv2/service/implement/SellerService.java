package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.repository.ProductRepo;
import com.cgshopeeappv2.repository.SellerRepo;
import com.cgshopeeappv2.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService implements ISellerService {
    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void save(Seller seller) {
        sellerRepo.save(seller);
    }

    @Override
    public Seller getSellerByAccount_username(String account) {
        return sellerRepo.findSellerByAccount_Username(account);
    }

    @Override
    public Seller getByAccountUsername(String username) {
        return sellerRepo.findByAccount_Username(username);
    }

}
