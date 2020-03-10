package com.dongisarang.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signUpCustomer(Customer customer) {
        String encryptPw = passwordEncoder.encode(customer.getCustomerPassword());

        customer.setCustomerPassword(encryptPw);

        customerRepository.save(customer);
    }

    public void changePassword(Customer customer, String changePassword) {
        customer.setCustomerPassword(passwordEncoder.encode(changePassword));
        customerRepository.save(customer);
    }
}
