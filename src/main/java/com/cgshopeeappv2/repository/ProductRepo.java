package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository <Product, Integer> {
    List <Product> findAllBySellerId(Integer sellerId);

    List <Product> findAllByCategoryId(Integer categoryId);

    @Query("SELECT p FROM Product p\n" +
            "WHERE p.name LIKE '%%'\n" +
            "ORDER BY CASE\n" +
            "    WHEN p.name LIKE CONCAT('%',:name1,'%') AND p.star >= 4 THEN 1\n" +
            "    WHEN p.name LIKE CONCAT('%',:name2,'%') AND p.star >= 4 THEN 2\n" +
            "    ELSE 3\n" +
            "END, p.sellNumber")
    Page <Product> findTrendbyName(String name1, String name2, Pageable pageable);
}
