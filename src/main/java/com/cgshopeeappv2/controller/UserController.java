package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.dto.ChangePasswordDTO;
import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.Bill;
import com.cgshopeeappv2.entity.CartItem;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.entity.TransactionInformation;
import com.cgshopeeappv2.entity.User;
import com.cgshopeeappv2.entity.UserAddress;
import com.cgshopeeappv2.entity.Wallet;
import com.cgshopeeappv2.repository.AccountRepo;
import com.cgshopeeappv2.repository.BillRepo;
import com.cgshopeeappv2.repository.SellerRepo;
import com.cgshopeeappv2.repository.TransactionInformationRepo;
import com.cgshopeeappv2.repository.UserAddressRepo;
import com.cgshopeeappv2.repository.UserRepo;
import com.cgshopeeappv2.repository.WalletRepo;
import com.cgshopeeappv2.service.IBillService;
import com.cgshopeeappv2.service.ICartItemService;
import com.cgshopeeappv2.service.IUserAddressService;
import com.cgshopeeappv2.service.IUserService;
import com.cgshopeeappv2.service.implement.TransactionInformationService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IUserAddressService iAddressUserService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IBillService billService;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TransactionInformationRepo transactionInformationRepo;
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SellerRepo sellerRepo;

//    @Autowired
//    private IAccountService iAccountService;

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private TransactionInformationService transactionInformationService;
    @Autowired
    private UserAddressRepo userAddressRepo;

//    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @RequestMapping("/information")
    public ModelAndView information(Model model, @AuthenticationPrincipal Account account, HttpServletRequest request) {
        model.addAttribute("user", iUserService.getUserByAccount(account.getUsername()));
        ModelAndView modelAndView = new ModelAndView("content/user-information");
        User user = iUserService.getUserByAccount(account.getUsername());
        List <UserAddress> list = iAddressUserService.getAllById(user.getId());
        User user1 = (User) request.getSession().getAttribute("user");
        System.out.println(user1.getName());
        return modelAndView;
    }

    @RequestMapping("/order")
    public ModelAndView order(
            @AuthenticationPrincipal Account account
    ) {
        User user = userRepo.getUserByAccount_Username(account.getUsername());

        List <Bill> bills = billRepo.findAllByUserIdOrderByDateTimeDesc(user.getId());
        ModelAndView modelAndView = new ModelAndView("content/order-user");
        modelAndView.addObject("bills", bills);
        return modelAndView;
    }

    @RequestMapping("/address")
    public ModelAndView address(@AuthenticationPrincipal Account account, Model model) {
        User user = iUserService.getUserByAccount(account.getUsername());
        List <UserAddress> list = iAddressUserService.getAllById(user.getId());
        ModelAndView modelAndView = new ModelAndView("content/address-user");
        modelAndView.addObject("address_user", user);
        modelAndView.addObject("list_address_user", list);
        return modelAndView;
    }

    @RequestMapping("/notify")
    public ModelAndView announcement() {
        ModelAndView modelAndView = new ModelAndView("content/notify-user");
        return modelAndView;
    }

    @RequestMapping("/wallet")
    public ModelAndView wallet(
            @AuthenticationPrincipal Account account
    ) {
        List <TransactionInformation> transactionInformations = transactionInformationRepo.findByWallet_UsernameOrderByLocalDateTimeDesc(account.getUsername());

        Wallet wallet = walletRepo.getReferenceById(account.getUsername());
        ModelAndView modelAndView = new ModelAndView("content/wallet");
        modelAndView.addObject("wallet", wallet);
        modelAndView.addObject("transactionInformations", transactionInformations);
        return modelAndView;
    }

    @RequestMapping("wallet/add")
    public String addMoney(
            @AuthenticationPrincipal Account account,
            @RequestParam("money") Integer money
    ) {
        Wallet wallet = walletRepo.getReferenceById(account.getUsername());
        wallet.setMoney(wallet.getMoney() + money);
        transactionInformationService.create(wallet, money, "Nạp tiền vào ví");
        walletRepo.saveAndFlush(wallet);
        return "redirect:/user/wallet";
    }

    @PostMapping("/change-information")
    public ModelAndView changeInformation(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            HttpServletRequest request

    ) {
        User user1 = userRepo.getReferenceById(user.getId());
        user1.setName(user.getName());
        user1.setGender(user.getGender());
        user1.setDateOfBirth(user.getDateOfBirth());
        iUserService.save(user1);
        request.getSession().setAttribute("user", user1);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
        return modelAndView;
    }

    @PostMapping("/create-address")
    public ModelAndView createAddress(@ModelAttribute UserAddress userAddress) {
        iAddressUserService.save(userAddress);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/address");
        return modelAndView;
    }

    @PostMapping("/update-address")
    public ModelAndView updateAddress(@ModelAttribute UserAddress userAddress, BindingResult bindingResult) {
        iAddressUserService.save(userAddress);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/address");
        return modelAndView;
    }

    @PostMapping("/delete-address")
    public ModelAndView deleteAddress(@RequestParam("id") int id) {
        iAddressUserService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/address");
        return modelAndView;
    }

    @PostMapping("/change-default-address")
    public String changeDefault(@RequestParam("id") int id,
                                @AuthenticationPrincipal Account account,
                                RedirectAttributes redirectAttributes) {
        User user = iUserService.getUserByAccount(account.getUsername());
        List <UserAddress> userAddresses = userAddressRepo.findAllByIP_Id(user.getId());
        for (UserAddress userAddress : userAddresses) {
            userAddress.setDefault_address("false");
        }
        userAddressRepo.saveAllAndFlush(userAddresses);
        UserAddress userAddress = userAddressRepo.findById(id);
        userAddress.setDefault_address("true");
        userAddressRepo.saveAndFlush(userAddress);
        redirectAttributes.addFlashAttribute("message", "Đổi địa chỉ thành công");
        return "redirect:/user/address";
    }

    @GetMapping("/cart")
    public ModelAndView cart(@AuthenticationPrincipal Account account) {
        ModelAndView modelAndView = new ModelAndView("content/cart-form");
        User user = iUserService.getUserByAccount(account.getUsername());
        List <CartItem> cartItems = cartItemService.getByUser(user);
        UserAddress userAddress = iAddressUserService.findByDefaultAddress(account);
        modelAndView.addObject("cartItems", cartItems);
        modelAndView.addObject("a", userAddress);
        List <Bill> bills = billService.createBill(account);
        Integer deliveryCharge = billService.deliveryCharge(account, bills);
        modelAndView.addObject("deliveryCharge", deliveryCharge);
        return modelAndView;
    }

    @GetMapping("/cart/add/{id}")
    public String addItem(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal Account account
    ) {
        User user = iUserService.getUserByAccount(account.getUsername());
        cartItemService.save(user, id);
        List <CartItem> cartItems = cartItemService.getByUser(user);
        return "redirect:/user/cart";
    }

//    @PostMapping("/upload-image")
//    public ModelAndView create(@RequestParam MultipartFile image, @AuthenticationPrincipal Account account) throws IOException {
//        Path staticPath = Paths.get("static");
//        Path imagePath = Paths.get("images");
//        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
//            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
//        }
//        Path file = CURRENT_FOLDER.resolve(staticPath)
//                .resolve(imagePath).resolve(image.getOriginalFilename());
//        try (OutputStream os = Files.newOutputStream(file)) {
//            os.write(image.getBytes());
//        }
//        User user = iUserService.getUserByAccount(account.getUsername());
//        String img = "http://localhost:8080/" + imagePath.resolve(image.getOriginalFilename()).toString();
//        user.setImg(img);
//        iUserService.save(user);
//        System.out.println(user);
//        ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
//        return modelAndView;
//    }

    @PostMapping("/upload-image")
    public ModelAndView create(@RequestParam MultipartFile image, @AuthenticationPrincipal Account account) throws IOException {
        User user = iUserService.getUserByAccount(account.getUsername());

        Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

        String imageUrl = (String) uploadResult.get("url");

        user.setImg(imageUrl);
        iUserService.save(user);

        ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
        return modelAndView;
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword(@AuthenticationPrincipal Account account, @ModelAttribute ChangePasswordDTO changePasswordDTO, BindingResult bindingResult) {
        boolean isValidOldPW = passwordEncoder.matches(changePasswordDTO.getOldPW(), account.getPassword());
        boolean isValidNewPW = changePasswordDTO.getNewPW().equals(changePasswordDTO.getNewAgainPW());
        if (isValidOldPW && isValidNewPW) {
            account.setPassword(changePasswordDTO.getNewPW());
            accountRepo.save(account);
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/user/information");
            return modelAndView;
        }
    }

    @RequestMapping("/seller-home")
    public String seller(
            @AuthenticationPrincipal Account account,
            HttpServletRequest request
    ) {
        Seller seller = sellerRepo.findByAccount_Username(account.getUsername());
        if (seller != null) {
            HttpSession session = request.getSession();
            session.setAttribute("seller", seller);
            return "redirect:/seller/product";
        } else {
            return "redirect:/seller/register";
        }
    }
}
