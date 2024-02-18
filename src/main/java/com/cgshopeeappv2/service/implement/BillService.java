package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.dto.DistanceAPI;
import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Bill;
import com.cgshopeeappv2.entity.BillItem;
import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.entity.SellerAddress;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.entity.UserAddress;
import com.cgshopeeappv2.repository.BillItemRepo;
import com.cgshopeeappv2.repository.BillRepo;
import com.cgshopeeappv2.repository.BillStatusRepo;
import com.cgshopeeappv2.repository.CartItemRepo;
import com.cgshopeeappv2.repository.SellerAddressRepo;
import com.cgshopeeappv2.repository.UserAddressRepo;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BillService implements IBillService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    BillItemRepo billItemRepo;
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    BillStatusRepo billStatusRepo;
    @Autowired
    UserAddressRepo userAddressRepo;
    @Autowired
    SellerAddressRepo sellerAddressRepo;

    public List <Bill> createBill(Account account) {
        List <Bill> bills = new ArrayList <>();
        User user = userRepo.getUserByAccount_Username(account.getUsername());
        List <CartItem> cartItems = cartItemRepo.findAllByUserId(user.getId());
        for (CartItem cartItem : cartItems) {
            Bill bill = new Bill();
            BillItem billItem = new BillItem(cartItem);
            bill.setBillItem(billItem);
            bill.setUser(user);
            bill.setSeller(cartItem.getProduct().getSeller());
            bill.setStatus(billStatusRepo.getReferenceById(1));
            if (userAddressRepo.findDefaultAddress(user.getId()) != null) {
                bill.setUserAddress(userAddressRepo.findDefaultAddress(user.getId()).toString());

            }
            bill.setTotalMoney((cartItem.getProduct().getSellPrice() * cartItem.getQuantity()));
            bills.add(bill);
        }
        return bills;
    }

    public int deliveryCharge(Account account, List <Bill> bills) {
        int charge = 0;
        User user = userRepo.getUserByAccount_Username(account.getUsername());
        UserAddress userAddress = userAddressRepo.findDefaultAddress(user.getId());
        if (userAddress == null) {
            return 0;
        }
        Set <Seller> sellers = new HashSet <>();
        for (Bill bill : bills) {
            sellers.add(bill.getSeller());
        }
        for (Seller seller : sellers) {
            int distance = 0;
            SellerAddress sellerAddress = sellerAddressRepo.findByIP_Id(seller.getId());
            String userX = userAddress.getDistrict().getX();
            String userY = "-" + userAddress.getDistrict().getY();
            String sellerX = sellerAddress.getDistrict().getX();
            String sellerY = "-" + sellerAddress.getDistrict().getY();
            String uri = "https://api.nextbillion.io/distancematrix/json?origins=" + userX + "," + userY + "&destinations=" + sellerX + "," + sellerY + "&mode=car&key=b98e9dd2f9414231bae19340b76feff0&avoid=highway";
            RestTemplate restTemplate = new RestTemplate();
            DistanceAPI result = restTemplate.getForObject(uri, DistanceAPI.class);
            if (result != null) {
                distance = result.getRows().get(0).getElements().get(0).getDistance().getValue();
            }
            charge += distance * 2;
        }
        return charge;
    }
}
