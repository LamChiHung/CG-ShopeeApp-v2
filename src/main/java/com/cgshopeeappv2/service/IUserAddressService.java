package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.UserAddress;

import java.util.List;

public interface IUserAddressService {
    List <UserAddress> getAllById(Integer id);


    void save(UserAddress userAddress);

    void deleteUserAddress(UserAddress userAddress);

    void deleteById(int id);

    void changeDefaultAddress(int id, int IP);

    UserAddress findById(int id);

    UserAddress findByDefaultAddress(Account account);
}
