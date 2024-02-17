package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Bill;

import java.util.List;

public interface IBillService {
    List <Bill> createBill(Account account);

    int deliveryCharge(Account account, List <Bill> bills);

}
