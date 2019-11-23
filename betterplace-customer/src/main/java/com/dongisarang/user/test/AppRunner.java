package com.dongisarang.user.test;

import com.dongisarang.user.customer.Customer;
import com.dongisarang.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        vincent();
        hskim();
    }

    private void hskim() {
    }

    private void vincent() {
        createVincentAccount();
    }

    private void createVincentAccount() {
        Customer customer = new Customer();
        customer.setCustomerId("gunflake09");
        customer.setCustomerPassword("1234");
        customer.setNickname("Vincent");
        customer.setEmail("gunflake09@naver.com");
        customer.setPhone("010-4117-7501");
        customerRepository.save(customer);
    }
}
