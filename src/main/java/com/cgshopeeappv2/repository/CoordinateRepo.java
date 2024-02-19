package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepo extends JpaRepository <Coordinates, Integer> {
}
