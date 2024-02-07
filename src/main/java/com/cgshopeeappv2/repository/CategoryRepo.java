package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository <Category, Integer> {
}
