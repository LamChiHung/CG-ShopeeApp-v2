package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends JpaRepository <Bill, Integer> {
    List <Bill> findAllByUserId(Integer id);

    List <Bill> findAllBySellerIdAndStatusId(Integer sellerId, Integer statusId);
}
