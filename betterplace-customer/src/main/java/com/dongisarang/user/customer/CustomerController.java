package com.dongisarang.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/my")
    public String profileManage(Model model){
        Customer customer = new Customer();
        customer.setNickName("Vincent");
        customer.setEmail("gunflake09@naver.com");
        customer.setPhone("010-4117-7501");
        model.addAttribute("customer", customer);
        return "Profile";
    }

}
