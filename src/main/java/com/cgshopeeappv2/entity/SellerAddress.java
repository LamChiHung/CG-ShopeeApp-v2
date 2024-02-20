package com.cgshopeeappv2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table
public class SellerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "IP", referencedColumnName = "id")
    private Seller IP;

    private String name;

    private String apartment_number;

    private String phone_number;

    private String city;

    @ManyToOne
    @JoinColumn(name = "district", referencedColumnName = "id_Coordinates")
    private Coordinates district;

    private String ward;

    @ColumnDefault("false")
    private String default_address;

    @Override
    public String toString() {
        return "Số điện thoại: " + phone_number + "," +
                "Địa chỉ: " + apartment_number + ", Phường " + ward + ", " + district.toString() + ", thành phố " + city;
    }

}
