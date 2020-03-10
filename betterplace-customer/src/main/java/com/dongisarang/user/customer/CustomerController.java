package com.dongisarang.user.customer;

import com.dongisarang.user.exception.NotFoundCustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Slf4j
@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/join")
    public String goSearch(){
        return "pages/join";
    }

    @GetMapping("/login")
    public String goLogin(){
        return "pages/login";
    }

    @GetMapping("/profile")
    public String goProfile(){
        return "pages/profile";
    }

    @GetMapping("/notice")
    public String goNotice() { return "pages/notice"; }

    @PostMapping("/notice")
    public String getNotice() {
        return "pages/notice";
    }

    /** 회원가입 후 로그인 페이지로 이동한다. */
    @PostMapping(value="/join")
    public String setJoin(Customer customer) {

        customerService.signUpCustomer(customer);

        return "redirect:/login";
    }

    /**
     * 파트너 마이페이지로 이동
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/mypage")
    public String initMypage(Model model, Principal principal){

        Customer customer = customerRepository.findByCustomerId(principal.getName()).orElseThrow(() -> new NotFoundCustomerException(principal.getName()));

        model.addAttribute("customer", customer);

        return "pages/mypage";
    }

    @GetMapping("/mypage/changepassword")
    public String initPasswordForm(){

        return "pages/changepassword";
    }

    /**
     * 파트너 비밀번호 변경 로직
     * @param currentPassword 기존 비밀번호
     * @param changePassword  새 비밀번호
     * @param principal
     * @return
     */
    @PostMapping("/mypage/changepassword")
    public String processPasswordForm(String currentPassword, String changePassword, Principal principal){

        Customer customer = customerRepository.findByCustomerId(principal.getName()).orElseThrow(() -> new NotFoundCustomerException(principal.getName()));

        // 현재 비밀번호 확인
        if(!passwordEncoder.matches(currentPassword,customer.getCustomerPassword()))
        {


            return "";
        }
        // 새 비밀번호 등록

        customerService.changePassword(customer, changePassword);

        return "redirect:/mypage";
    }
}
