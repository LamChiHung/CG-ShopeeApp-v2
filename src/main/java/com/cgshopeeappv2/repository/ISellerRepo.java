package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller, Integer> {
    Seller findSellerByAccount_Username(String user_name);
}
