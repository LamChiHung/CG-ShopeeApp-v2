package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.TransactionInformation;
import com.cgshopeeappv2.entity.Wallet;

public interface ITransactionService {
    public TransactionInformation create(Wallet wallet, Integer money, String content);
}
