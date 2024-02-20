package com.cgshopeeappv2.dto;

import com.cgshopeeappv2.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {

    private String oldPW;
    private String newPW;
    private String newAgainPW;

}
