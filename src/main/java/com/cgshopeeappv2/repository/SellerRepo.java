package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer> {
    Seller findByAccount_Username(String username);

    Seller findSellerByAccount_Username(String user_name);



}
