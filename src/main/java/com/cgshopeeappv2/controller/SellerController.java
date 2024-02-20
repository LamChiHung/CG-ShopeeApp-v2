package com.cgshopeeappv2.controller;

import com.cgshopeeappv2.entity.Account;
import com.cgshopeeappv2.entity.AccountRole;
import com.cgshopeeappv2.entity.Bill;
import com.cgshopeeappv2.entity.Category;
import com.cgshopeeappv2.entity.Product;
import com.cgshopeeappv2.entity.Seller;
import com.cgshopeeappv2.entity.SellerAddress;
import com.cgshopeeappv2.entity.Wallet;
import com.cgshopeeappv2.repository.AccountRoleRepo;
import com.cgshopeeappv2.repository.BillItemRepo;
import com.cgshopeeappv2.repository.BillRepo;
import com.cgshopeeappv2.repository.BillStatusRepo;
import com.cgshopeeappv2.repository.CoordinateRepo;
import com.cgshopeeappv2.repository.RoleRepo;
import com.cgshopeeappv2.repository.SellerAddressRepo;
import com.cgshopeeappv2.repository.SellerRepo;
import com.cgshopeeappv2.repository.UserAddressRepo;
import com.cgshopeeappv2.repository.WalletRepo;
import com.cgshopeeappv2.service.IBillService;
import com.cgshopeeappv2.service.ICategoryService;
import com.cgshopeeappv2.service.IProductService;
import com.cgshopeeappv2.service.ISellerService;
import com.cgshopeeappv2.service.ITransactionService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private ISellerService iSellerService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ISellerService sellerService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private BillItemRepo billItemRepo;
    @Autowired
    private IBillService billService;
    @Autowired
    private UserAddressRepo userAddressRepo;
    @Autowired
    private BillStatusRepo billStatusRepo;
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private CoordinateRepo coordinateRepo;
    @Autowired
    private SellerAddressRepo sellerAddressRepo;
    @Autowired
    private AccountRoleRepo accountRoleRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private SellerRepo sellerRepo;

    @RequestMapping("")
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

    @GetMapping("/product")
    public ModelAndView product(@AuthenticationPrincipal Account account) {
        String username = account.getUsername();
        Seller seller = sellerService.getByAccountUsername(username);
        List<Product> products = productService.getAllBySellerId(seller.getId());
        ModelAndView modelAndView = new ModelAndView("content/product-management");
        modelAndView.addObject("products", products);
        Product product = new Product();
        modelAndView.addObject("product", product);
        List<Category> categories = categoryService.getAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/product")
    public String product(
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal Account account,
            @Validated @ModelAttribute("product") Product product, BindingResult bindingResult,
            @RequestParam MultipartFile img) throws IOException {
        if (!img.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");
            product.setImg(imageUrl);
        }
        productService.save(product, account);
        redirectAttributes.addFlashAttribute("message", "Lưu sản phẩm thành công");
        return "redirect:/seller/product";
    }

    @GetMapping("/delete/product/{id}")
    public String delete(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal Account account,
            RedirectAttributes redirectAttributes
    ) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công");
        return "redirect:/seller/product";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("content/seller-register-form");
        return modelAndView;
    }

    @PostMapping("/register")
    public String sellerRegister(
            @AuthenticationPrincipal Account account,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "phone_number", defaultValue = "") String phoneNumber,
            @RequestParam(name = "city", defaultValue = "") String city,
            @RequestParam(name = "district", defaultValue = "") String district,
            @RequestParam(name = "ward", defaultValue = "") String ward,
            @RequestParam(name = "apartment_number", defaultValue = "") String apartment,
            RedirectAttributes redirectAttributes
    ) {
        if (name.isEmpty() || phoneNumber.isEmpty() || city.isEmpty() || district.isEmpty() || ward.isEmpty() || apartment.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng điền đầy đủ thông tin");
            return "redirect:/seller/register";
        } else {
            AccountRole accountRole = new AccountRole();
            accountRole.setRole(roleRepo.getReferenceById("ROLE_SELLER"));
            accountRole.setAccount(account);
            accountRoleRepo.saveAndFlush(accountRole);
            Seller seller = new Seller();
            seller.setAccount(account);
            seller.setName(name);
            sellerRepo.saveAndFlush(seller);
            SellerAddress sellerAddress = new SellerAddress();
            sellerAddress.setName(name);
            sellerAddress.setIP(sellerService.getByAccountUsername(account.getUsername()));
            sellerAddress.setDefault_address("true");
            sellerAddress.setWard(ward);
            sellerAddress.setApartment_number(apartment);
            sellerAddress.setCity(city);
            sellerAddress.setDistrict(coordinateRepo.getReferenceById(Integer.parseInt(district)));
            sellerAddressRepo.saveAndFlush(sellerAddress);
            redirectAttributes.addFlashAttribute("message", "Đăng ký cửa hàng thành công, hãy bắt đầu thêm sản phẩm cho cửa hàng của bạn");
            return "redirect:/seller/product";
        }
    }

    @RequestMapping("/statistics")
    public String statistics(Model model, @AuthenticationPrincipal Account account, @RequestParam(name = "month", defaultValue = "1") int monthly ) {
        Seller seller = iSellerService.getSellerByAccount_username(account.getUsername());
        model.addAttribute("listMonth",
                billService.getTotalMoneyByMonthInYearAndSeller(2024, seller.getId()));
        model.addAttribute("listQuantity", billService.findTop5ProductQuantitiesByMonth(monthly));
        model.addAttribute("listName", billService.findTop5ProductNamesByMonthAndStatus(monthly));
        model.addAttribute("TotalQuantity", billService.getTotalQuantityByMonthAndSeller(monthly, seller.getId()));
        return "content/seller-statistics";
    }


    @RequestMapping("/information")
    public ModelAndView information(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("seller", iSellerService.getSellerByAccount_username(account.getUsername()));
        ModelAndView modelAndView = new ModelAndView("content/seller-information");
        return modelAndView;
    }

    @RequestMapping("/bill")
    public ModelAndView bill(
            @AuthenticationPrincipal Account account
    ) {
        Seller seller = sellerService.getByAccountUsername(account.getUsername());
        List<Bill> bills = billRepo.findAllBySellerIdAndStatusId(seller.getId(), 1);
        ModelAndView modelAndView = new ModelAndView("content/bill-management");
        modelAndView.addObject("bills", bills);

        return modelAndView;
    }

    @RequestMapping("/bill/confirm")
    public String billConfirm(
            @AuthenticationPrincipal Account account,
            @RequestParam(name = "id") Integer id
    ) {
        Bill bill = billRepo.findById(id).orElse(null);
        if (bill != null) {
            bill.setStatus(billStatusRepo.getReferenceById(2));
            Product product = productService.getById(bill.getBillItem().getProductId());
            product.setSellNumber(product.getSellNumber() + 1);
            Wallet wallet = walletRepo.getReferenceById(account.getUsername());
            wallet.setMoney(wallet.getMoney() + bill.getTotalMoney());
            transactionService.create(wallet, bill.getTotalMoney(), "Nhận tiền hóa đơn từ khách hàng " + bill.getUser().getName());
            productService.save(product, account);
            walletRepo.saveAndFlush(wallet);
            billRepo.saveAndFlush(bill);
        }
        return "redirect:/seller/bill";
    }

    @RequestMapping("/voucher")
    public ModelAndView voucher() {
        ModelAndView modelAndView = new ModelAndView("content/voucher-management");
        return modelAndView;
    }

    @RequestMapping("/history")
    public ModelAndView history(
            @AuthenticationPrincipal Account account
    ) {
        Seller seller = sellerService.getByAccountUsername(account.getUsername());
        List<Bill> bills = billRepo.findAllBySellerIdAndStatusId(seller.getId(), 2);
        ModelAndView modelAndView = new ModelAndView("content/seller-history");
        modelAndView.addObject("bills", bills);
        return modelAndView;
    }


    @PostMapping("/change-information")
    public String changeInfo(@Valid @ModelAttribute("seller") Seller seller, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "redirect:/seller/information";
        }
        iSellerService.save(seller);
        return "redirect:/seller/information";
    }

    @PostMapping("/upload-image")
    public ModelAndView changeInfo(@RequestParam MultipartFile image, @AuthenticationPrincipal Account account, RedirectAttributes redirect) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        Seller seller = iSellerService.getSellerByAccount_username(account.getUsername());
        String imageUrl = (String) uploadResult.get("url");
        seller.setImg(imageUrl);
        iSellerService.save(seller);
        ModelAndView modelAndView = new ModelAndView("redirect:/seller/information");
        return modelAndView;
    }
}
