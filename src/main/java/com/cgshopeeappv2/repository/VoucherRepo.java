package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepo extends JpaRepository <Voucher, String> {

}
