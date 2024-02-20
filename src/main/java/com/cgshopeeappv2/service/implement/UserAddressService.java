package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.entity.UserAddress;
import com.cgshopeeappv2.repository.UserAddressRepo;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService implements IUserAddressService {

    @Autowired
    private UserAddressRepo userAddressRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List <UserAddress> getAllById(Integer id) {
        return userAddressRepo.findAllByIP_Id(id);
    }


    @Override
    public void save(UserAddress userAddress) {
        if (userAddressRepo.findDefaultAddress(userAddress.getIP().getId()) == null) {
            userAddress.setDefault_address("true");
        }
        ;
        userAddressRepo.saveAndFlush(userAddress);
    }

    @Override
    public void deleteUserAddress(UserAddress userAddress) {
        userAddressRepo.delete(userAddress);
    }

    @Override
    public void deleteById(int id) {
        userAddressRepo.deleteById(id);
    }

    @Override
    public void changeDefaultAddress(int id, int IP) {
        List <UserAddress> userAddressesList = userAddressRepo.findAllByIdNotAndIP_Id(id, IP);
        for (UserAddress address : userAddressesList) {
            address.setDefault_address("false");
            save(address);
        }
    }

    @Override
    public UserAddress findById(int id) {
        return userAddressRepo.findById(id);
    }

    public UserAddress findByDefaultAddress(Account account) {
        User user = userRepo.getUserByAccount_Username(account.getUsername());
        return userAddressRepo.findDefaultAddress(user.getId());
    }
}
