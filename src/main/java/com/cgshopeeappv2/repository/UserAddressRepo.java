package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepo extends JpaRepository <UserAddress, Integer> {
    List <UserAddress> findAllByIP_Id(int id);

    void deleteById(int id);

    UserAddress findById(int id);

    List <UserAddress> findAllByIdNotAndIP_Id(int id, int IP);

    @Query("SELECT ua FROM UserAddress ua WHERE ua.IP.id = :userId AND ua.default_address = 'true'")
    UserAddress findDefaultAddress(Integer userId);
}

