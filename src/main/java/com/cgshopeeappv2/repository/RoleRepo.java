package com.cgshopeeappv2.repository;

import com.cgshopeeappv2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository <Role, String> {
}
