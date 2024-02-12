package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;

public interface IRegisterService {
    void createNewUserAccount(String username, String password);

    Account findById(String username);
}
