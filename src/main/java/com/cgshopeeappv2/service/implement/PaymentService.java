package com.cgshopeeappv2.service.implement;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Bill;
import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.entity.Wallet;
import com.cgshopeeappv2.repository.BillItemRepo;
import com.cgshopeeappv2.repository.BillRepo;
import com.cgshopeeappv2.repository.CartItemRepo;
import com.cgshopeeappv2.repository.WalletRepo;
import com.cgshopeeappv2.service.IBillService;
import com.cgshopeeappv2.service.ICartItemService;
import com.cgshopeeappv2.service.IPaymentService;
import com.cgshopeeappv2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IBillService billService;
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private ICartItemService cartItemService;
    @Autowired
    private IUserService userService;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private BillItemRepo billItemRepo;

    public String payment(Account account) {
        try {
            List <Bill> bills = billService.createBill(account);
            int deliveryCharge = billService.deliveryCharge(account, bills);
            Integer totalMoney = 0;
            Wallet fromWallet = walletRepo.findById(account.getUsername()).orElse(null);

            for (Bill bill : bills) {
                totalMoney += bill.getTotalMoney();
            }
            totalMoney += deliveryCharge;
            if (fromWallet.getMoney() < totalMoney) {
                return "Ví không đủ tiền thanh toán!";
            } else {
                for (Bill bill : bills) {
                    fromWallet.setMoney(fromWallet.getMoney() - bill.getTotalMoney());
                    billItemRepo.saveAndFlush(bill.getBillItem());
                    bill.setDateTime(LocalDateTime.now());
                }
                billRepo.saveAllAndFlush(bills);
                fromWallet.setMoney(fromWallet.getMoney() - deliveryCharge);
                List <CartItem> cartItems = cartItemService.getByUser(userService.getUserByAccount(account.getUsername()));
                cartItemRepo.deleteAll(cartItems);
                return "Thanh toán thành công";
            }
        } catch (Exception e) {
            return "Có lỗi xảy ra";
        }
    }
}
