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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[^0-9~`@!#$%^&*()_+{};:'<>/?]+$", message = "Tên không được chứa số hay ký tự đặc biệt")
    @Length(min = 2, max = 100, message = "Tên dài 2-100 ký tự")
    private String name;

    @Size(min = 0, max = 2, message = "Giới tính không phù hợp")
    private Integer gender;

    @Past
    private LocalDateTime dateOfBirdth;

    private String img;

    @OneToOne
    @JoinColumn(name = "account_username", referencedColumnName = "username")
    private Account account;

}
