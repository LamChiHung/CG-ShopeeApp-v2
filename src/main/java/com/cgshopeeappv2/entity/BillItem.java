package com.cgshopeeappv2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private String productName;
    private Integer sellerId;
    private Integer unitPrice;
    private Integer quantity;
    private String img;

    public BillItem(CartItem cartItem) {
        this.productId = cartItem.product.getId();
        this.productName = cartItem.product.getName();
        this.sellerId = cartItem.product.getSeller().getId();
        this.unitPrice = cartItem.product.getSellPrice();
        this.quantity = cartItem.getQuantity();
        this.img = cartItem.getProduct().getImg();
    }
}
