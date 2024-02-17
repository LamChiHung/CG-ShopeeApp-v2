package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository <Wallet, String> {
}
