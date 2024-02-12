package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.AccountRole;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.repository.AccountRepo;
import com.cgshopeeappv2.repository.AccountRoleRepo;
import com.cgshopeeappv2.repository.RoleRepo;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService implements IRegisterService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    AccountRoleRepo accountRoleRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public void createNewUserAccount(String username, String password) {
//        Tạo account
        Account account = new Account(username, password);
//        Tạo role
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(roleRepo.findById("ROLE_USER").orElse(null));
        List <AccountRole> accountRoles = new ArrayList <>();
        accountRoles.add(accountRole);
        account.setAuthorities(accountRoles);
        accountRepo.save(account);
        accountRoleRepo.save(accountRole);
//        Tạo user
        User user = new User();
        user.setName("Anonymous");
        user.setAccount(account);
        userRepo.save(user);


    }

    public Account findById(String username) {
        return accountRepo.findById(username).orElse(null);
    }
}
