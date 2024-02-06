package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.AccountRole;
import com.cgshopeeappv2.repository.AccountRepo;
import com.cgshopeeappv2.repository.AccountRoleRepo;
import com.cgshopeeappv2.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewAccount {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    AccountRoleRepo accountRoleRepo;
    @Autowired
    AccountRepo accountRepo;

    public void create() {
        Account account = new Account("lamchihung24@gmail.com", "Hoanglekha123");
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(roleRepo.findById("ROLE_USER").orElse(null));
        List <AccountRole> accountRoles = new ArrayList <>();
        accountRoles.add(accountRole);
        account.setAuthorities(accountRoles);
        accountRepo.save(account);
        accountRoleRepo.save(accountRole);
    }

}
