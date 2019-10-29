package com.dongisarang.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder pwEncoder;

    public void signUpCustomer(Customer customer) {
        Customer signUpCustomer = new Customer();

        String encryptPw = pwEncoder.encode(customer.getCustomerPassword());

        signUpCustomer.setCustomerNo(customer.getCustomerNo());
        signUpCustomer.setCustomerId(customer.getCustomerId());
        signUpCustomer.setCustomerPassword(encryptPw);
        signUpCustomer.setEmail(customer.getEmail());

        customerRepository.save(signUpCustomer);
    }

    public boolean loginCustomer(Customer customer) {
        return false;
    }

    public boolean changePassword(String currentPassword, String changePassword) {
        Customer getCustomer = customerRepository.findByCustomerIdAndCustomerPassword("gunflake09", currentPassword);

        //유저정보가 일치하면
        if(getCustomer !=null){
            getCustomer.setCustomerPassword(changePassword);
            Customer saveConfirm = customerRepository.save(getCustomer);

            if(saveConfirm.getCustomerId().equals("gunflake09")){
                return true;
            }
        }

        return false;
    }
}
