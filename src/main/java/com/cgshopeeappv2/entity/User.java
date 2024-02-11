package com.cgshopeeappv2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[^0-9~`@!#$%^&*()_+{};:'<>/?]+$", message = "Tên không được chứa số hay ký tự đặc biệt")
    @Length(min = 2, max = 100, message = "Tên dài 2-100 ký tự")
    private String name;

    private String gender;

    @Past
    private LocalDate dateOfBirth;

    private String img;

    @OneToOne
    @JoinColumn(name = "account_username", referencedColumnName = "username")
    private Account account;


}
