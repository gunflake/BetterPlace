package com.betterplace.dev.test;

import com.betterplace.dev.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    public String home(){
        return "Home";
    }

    @RequestMapping("/getCustomer")
    public String getCustomer(Model model){
        model.addAttribute("customer", customerRepository.findAll());
        return "Customer";
    }
}
