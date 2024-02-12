package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.UserAddress;
import com.cgshopeeappv2.repository.AddressUserRepo;
import com.cgshopeeappv2.service.IAddressUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService implements IAddressUserService {

    @Autowired
    private AddressUserRepo addressUserRepo;

    @Override
    public List<UserAddress> getAllById(Integer id) {
        return addressUserRepo.findAllByIP_Id(id);
    }


    @Override
    public void save(UserAddress userAddress) {
        addressUserRepo.save(userAddress);
    }

    @Override
    public void deleteUserAddress(UserAddress userAddress) {
        addressUserRepo.delete(userAddress);
    }

    @Override
    public void deleteById(int id) {
        addressUserRepo.deleteById(id);
    }

    @Override
    public void changeDefaultAddress(int id, int IP) {
        List<UserAddress> userAddressesList = addressUserRepo.findAllByIdNotAndIP_Id(id, IP);
        for (UserAddress address : userAddressesList) {
            address.setDefault_address("false");
            save(address);
        }
    }

    @Override
    public UserAddress findById(int id) {
        return addressUserRepo.findById(id);
    }
}
