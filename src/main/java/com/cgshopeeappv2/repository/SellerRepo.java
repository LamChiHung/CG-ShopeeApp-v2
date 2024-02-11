package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository <Seller, Integer> {
    Seller findByAccount_Username(String username);
    Seller findSellerByAccount_Username(String user_name);

}
