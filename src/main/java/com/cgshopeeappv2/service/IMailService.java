package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;
import jakarta.mail.MessagingException;

public interface IMailService {
    void verifyAccount(Account account) throws MessagingException;
}
