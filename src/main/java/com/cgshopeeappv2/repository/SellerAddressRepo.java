package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.SellerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAddressRepo extends JpaRepository <SellerAddress, Integer> {
    SellerAddress findByIP_Id(Integer ip);
}
