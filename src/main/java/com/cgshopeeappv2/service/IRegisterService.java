package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;

public interface IRegisterService {
    Account createNewUserAccount(String username, String password);

    Account findById(String username);

    void enableAccount(String email, String code);
}
