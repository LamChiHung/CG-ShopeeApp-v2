package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAllBySellerId(Integer sellerId);


    Page<Product> findAllBySellerIdOrderByDateTimeDesc(Integer sellerId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.seller.id = :sellerId AND LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Product> findSimilarProductsBySellerId(
            @Param("sellerId") Integer sellerId,
            @Param("searchTerm") String searchTerm,
            Pageable pageable
    );

    List<Product> findAllByCategoryId(Integer categoryId);

    @Query("SELECT p FROM Product p\n" +
            "WHERE p.name LIKE '%%'\n" +
            "ORDER BY CASE\n" +
            "    WHEN p.name LIKE CONCAT('%',:name1,'%') AND p.star >= 4 THEN 1\n" +
            "    WHEN p.name LIKE CONCAT('%',:name2,'%') AND p.star >= 4 THEN 2\n" +
            "    WHEN p.star >= 5 THEN 3\n" +
            "    WHEN p.star >= 4 THEN 4\n" +
            "    ELSE 8\n" +
            "END, p.sellNumber")
    Page<Product> findTrendbyName(String name1, String name2, Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE p.name LIKE CONCAT('%',:keyword,'%')" +
            "AND p.sellPrice between :from AND :to " +
            "AND p.category.id IN :categories AND p.star >= :star "
    )
    Page<Product> findBySearch(String keyword, List<Integer> categories, Integer from, Integer to, Integer star, Pageable pageable);
}
