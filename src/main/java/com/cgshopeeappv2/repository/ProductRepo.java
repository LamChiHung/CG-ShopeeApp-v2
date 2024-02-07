package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository <Product, Integer> {
    List <Product> findAllBySellerId(Integer sellerId);

    List <Product> findAllByCategoryId(Integer categoryId);
}
