package com.betterplace.dev.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/")
    public List<Customer> createCustomer(@RequestBody Map<String, String> param){
        String name = param.get("name");
        String phone = param.get("phone");
        Integer age = Integer.valueOf(param.get("age"));
        Customer customer = Customer.builder().name(name).phone(phone).age(age).build();
        customerRepository.save(customer);

        return customerRepository.findAll();
    }

    @RequestMapping("{name}")
    public List<Customer> getCustomer(@PathVariable String name){
        List<Customer> customers = customerRepository.findByNameLike(name);
        return customers;
    }

}
