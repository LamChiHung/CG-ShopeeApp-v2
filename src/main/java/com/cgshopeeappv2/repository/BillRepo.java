package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepo extends JpaRepository <Bill, Integer> {
}
