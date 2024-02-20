package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.TransactionInformation;
import com.cgshopeeappv2.entity.Wallet;
import com.cgshopeeappv2.repository.TransactionInformationRepo;
import com.cgshopeeappv2.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionInformationService implements ITransactionService {
    @Autowired
    TransactionInformationRepo transactionInformationRepo;

    @Override
    public TransactionInformation create(Wallet wallet, Integer money, String content) {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setWallet(wallet);
        transactionInformation.setMoney(money);
        transactionInformation.setContent(content);
        transactionInformation.setLocalDateTime(LocalDateTime.now());
        transactionInformationRepo.saveAndFlush(transactionInformation);
        return transactionInformation;
    }
}
