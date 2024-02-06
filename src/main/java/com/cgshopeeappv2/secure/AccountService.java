package com.cgshopeeappv2.secure;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.repository.AccountRepo;
import com.cgshopeeappv2.repository.AccountRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountRoleRepo accountRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findById(username).orElse(null);
        if (account != null) {
            account.setAuthorities(accountRoleRepo.findAllByAccount_Username(username));
        }
        return account;
    }
}
