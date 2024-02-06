package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepo extends JpaRepository <AccountRole, Integer> {
    List <AccountRole> findAllByAccount_Username(String username);
}
