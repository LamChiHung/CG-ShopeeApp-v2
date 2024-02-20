package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Bill;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBillService {
    List <Bill> createBill(Account account);

    int deliveryCharge(Account account, List <Bill> bills);

   List<Integer>  getTotalMoneyByMonthInYearAndSeller(@Param("year") int year, @Param("sellerId") int sellerId);

    List<Integer> findTop5ProductQuantitiesByMonth(@Param("month") int month);
    List<String> findTop5ProductNamesByMonthAndStatus(@Param("month") int month);
    Integer getTotalQuantityByMonthAndSeller(@Param("month") int month, @Param("sellerId") int sellerId);

}
