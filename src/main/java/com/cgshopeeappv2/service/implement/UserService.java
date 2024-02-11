package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(User user) {
      userRepo.save(user) ;
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepo.getUserByAccount_Username(account);
    }
}
