package com.cgshopeeappv2.repository;
import com.cgshopeeappv2.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends JpaRepository <Bill, Integer> {
    List <Bill> findAllByUserIdOrderByDateTimeDesc(Integer id);

    List <Bill> findAllBySellerIdAndStatusId(Integer sellerId, Integer statusId);

    @Query("SELECT COALESCE(SUM(b.totalMoney), 0) " +
            "FROM Bill b " +
            "WHERE YEAR(b.dateTime) = :year AND b.seller.id = :sellerId AND MONTH(b.dateTime) = :month and b.status.id= 2")
    Integer getTotalMoneyByMonthInYearAndSeller(@Param("year") int year, @Param("sellerId") int sellerId, @Param("month") int month);

    @Query("SELECT SUM(bi.quantity) " +
            "FROM Bill b " +
            "JOIN b.billItem bi " +
            "WHERE MONTH(b.dateTime) = :month " +
            "AND b.status.id = 2 " +
            "GROUP BY bi.productName " +
            "ORDER BY SUM(bi.quantity) DESC " +
            "LIMIT 5")
    List<Integer> findTop5ProductQuantitiesByMonth(@Param("month") int month);

    @Query("SELECT bi.productName " +
            "FROM Bill b " +
            "JOIN b.billItem bi " +
            "WHERE MONTH(b.dateTime) = :month " +
            "AND b.status.id = 2 " +
            "GROUP BY bi.productName " +
            "ORDER BY SUM(bi.quantity) DESC " +
            "LIMIT 5")
    List<String> findTop5ProductNamesByMonthAndStatus(@Param("month") int month);


    @Query("SELECT SUM(bi.quantity) " +
            "FROM Bill b " +
            "JOIN b.billItem bi " +
            "WHERE MONTH(b.dateTime) = :month " +
            "AND b.seller.id = :sellerId " +
            "AND b.status.id = 2")
    Integer getTotalQuantityByMonthAndSeller(@Param("month") int month, @Param("sellerId") int sellerId);

}
