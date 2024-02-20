package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.TransactionInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionInformationRepo extends JpaRepository <TransactionInformation, Integer> {
    List <TransactionInformation> findByWallet_UsernameOrderByLocalDateTimeDesc(String username);
}
