package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.repository.ProductRepo;
import com.cgshopeeappv2.repository.SellerRepo;
import com.cgshopeeappv2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    SellerRepo sellerRepo;

    public void save(Product product, Account account) {
        if (product.getId() == null) {
            String username = account.getUsername();
            Seller seller = sellerRepo.findByAccount_Username(username);
            product.setSeller(seller);
            product.setStar(5F);
            productRepo.saveAndFlush(product);
        } else {
            Product product1 = productRepo.findById(product.getId()).orElse(null);
            product1.setName(product.getName());
            product1.setCategory(product.getCategory());
            product1.setOriginPrice(product.getOriginPrice());
            product1.setSellPrice(product.getSellPrice());
            product1.setImg(product.getImg());
            product1.setQuantity(product.getQuantity());
            productRepo.save(product1);
        }

    }

    public void delete(Integer id) {
        productRepo.deleteById(id);
    }


    public void getAllByPage(Pageable page) {
        productRepo.findAll(page);
    }

    public List <Product> getAllBySellerId(Integer sellerId) {
        return productRepo.findAllBySellerId(sellerId);
    }

    @Override
    public Product getById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Page <Product> findTrendByName(String name1, String name2, Pageable pageable) {
        return productRepo.findTrendbyName(name1, name2, pageable);
    }
}
