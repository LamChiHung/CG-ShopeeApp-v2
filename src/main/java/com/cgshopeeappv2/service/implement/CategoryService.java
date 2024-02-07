package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Category;
import com.cgshopeeappv2.repository.CategoryRepo;
import com.cgshopeeappv2.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List <Category> getAll() {
        return categoryRepo.findAll();
    }
}
