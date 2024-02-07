package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    void save(Product product, Account account);

    void delete(Integer id);


    void getAll(Pageable page);

    List <Product> getAllBySellerId(Integer sellerId);

    Product getById(Integer id);
}
