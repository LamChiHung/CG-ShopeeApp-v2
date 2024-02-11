package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.User;

public interface IUserService {
    User getUserByAccount (String account);
    void save(User user);
}
