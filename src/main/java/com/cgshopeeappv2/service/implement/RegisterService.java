package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.AccountRole;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.repository.AccountRepo;
import com.cgshopeeappv2.repository.AccountRoleRepo;
import com.cgshopeeappv2.repository.RoleRepo;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.service.IRegisterService;
import com.cgshopeeappv2.util.IRandomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService implements IRegisterService {
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountRoleRepo accountRoleRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private IRandomCode randomCode;

    @Override
    public Account createNewUserAccount(String username, String password) {
//        Tạo account
        Account account = new Account(username, password);
        account.setCode(randomCode.randomCode());
//        Tạo role
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(roleRepo.findById("ROLE_USER").orElse(null));
        List <AccountRole> accountRoles = new ArrayList <>();
        accountRoles.add(accountRole);
        account.setAuthorities(accountRoles);
        accountRepo.saveAndFlush(account);
        accountRoleRepo.saveAndFlush(accountRole);
//        Tạo user
        User user = new User();
        user.setName("Anonymous");
        user.setAccount(account);
        userRepo.saveAndFlush(user);
        return account;
    }

    public Account findById(String username) {
        return accountRepo.findById(username).orElse(null);
    }


    public void enableAccount(String email, String code) {
        Account account = accountRepo.findById(email).orElse(null);
        if (account != null) {
            if (account.getCode().equals(code)) {
                account.setEnabled(true);
                accountRepo.saveAndFlush(account);
            }
        }
    }
}
