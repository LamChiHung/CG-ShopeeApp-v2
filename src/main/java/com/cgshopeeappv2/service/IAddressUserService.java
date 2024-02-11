package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.UserAddress;

import java.util.List;

public interface IAddressUserService {
    List<UserAddress> getAllById(Integer id);


    void save (UserAddress userAddress);

    void deleteUserAddress(UserAddress userAddress);

    void deleteById(int id);
}
