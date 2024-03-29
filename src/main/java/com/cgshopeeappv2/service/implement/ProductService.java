package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.repository.ProductRepo;
import com.cgshopeeappv2.repository.SellerRepo;
import com.cgshopeeappv2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SellerRepo sellerRepo;

    public void save(Product product, Account account) {
        if (product.getId() == null) {
            String username = account.getUsername();
            Seller seller = sellerRepo.findByAccount_Username(username);
            product.setSeller(seller);
            product.setDateTime(LocalDateTime.now());
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
            product1.setDateTime(LocalDateTime.now());
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

    public Page <Product> search(String keyword, String categories, Integer from, Integer to, Integer star, String sort, Integer page) {
        List <String> categoryList = Arrays.stream(categories.split(",")).toList();
        List <Integer> categoryListId = new ArrayList <>();
        for (String category : categoryList) {
            categoryListId.add(Integer.parseInt(category));
        }
        if (sort.isEmpty()) {
            sort = "sold";
        }
        Sort sortToFind = null;
        switch (sort) {
            case "star":
                sortToFind = Sort.by("star").descending();
                break;
            case "sold":
                sortToFind = Sort.by("sellNumber").descending();
                break;
            case "asc":
                sortToFind = Sort.by("sellPrice").ascending();
                break;
            case "desc":
                sortToFind = Sort.by("sellPrice").descending();
                break;
        }
        Pageable pageable = PageRequest.of(page, 10, sortToFind);
        return productRepo.findBySearch(keyword, categoryListId, from, to, star, pageable);
    }

//    @Override
//    public Page<Product> findAllBySellerId(Integer id, Pageable pageable) {
//        return productRepo.findAllBySellerId(id,pageable);
//    }


    @Override
    public Page<Product> findAllBySellerIdOrderByDate_timeDesc(Integer id, Pageable pageable) {
        return productRepo.findAllBySellerIdOrderByDateTimeDesc(id,pageable);
    }

    @Override
    public Page<Product> findSimilarProductsBySellerId(Integer sellerId, String searchTerm, Pageable pageable) {
        return productRepo.findSimilarProductsBySellerId(sellerId,searchTerm,pageable);
    }



}
