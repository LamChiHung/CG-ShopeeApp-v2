package com.cgshopeeappv2.service;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface IProductService {
    void save(Product product, Account account);

    void delete(Integer id);

    void getAllByPage(Pageable page);

    List <Product> getAllBySellerId(Integer sellerId);


    Product getById(Integer id);

    Page <Product> findTrendByName(String name1, String name2, Pageable pageable);

    Page <Product> search(String keyword, String categories, Integer from, Integer to, Integer star, String sort, Integer page);

//    Page<Product> findAllBySellerId(Integer id, Pageable pageable);
    Page<Product> findAllBySellerIdOrderByDate_timeDesc(Integer id, Pageable pageable);




    Page<Product> findSimilarProductsBySellerId(
            @Param("sellerId") Integer sellerId,
            @Param("searchTerm") String searchTerm,
            Pageable pageable
    );

}
