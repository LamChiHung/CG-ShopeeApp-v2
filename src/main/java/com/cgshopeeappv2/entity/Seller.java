package com.cgshopeeappv2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[^0-9~`@!#$%^&*()_+{};:'<>/?]+$", message = "Tên không được chứa số hay ký tự đặc biệt")
    @Length(min = 2, max = 50, message = "Tên dài 2-50 ký tự")
    private String name;

    private String img;

    @OneToOne
    @JoinColumn(name = "account_username", referencedColumnName = "username")
    private Account account;

    private String address;

    private String horizontal_axis;

    private String vertical_axis;
    private String phoneNumber;
    private String linkFacebook;




}
