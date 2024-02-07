package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ISellerRepo extends JpaRepository<Seller, Integer> {
    Seller findSellerByAccount_Username(String user_name);

}
