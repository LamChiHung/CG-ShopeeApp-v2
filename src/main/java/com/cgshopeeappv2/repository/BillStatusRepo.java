package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillStatusRepo extends JpaRepository <BillStatus, Integer> {
}
