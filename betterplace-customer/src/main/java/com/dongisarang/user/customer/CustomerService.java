package com.dongisarang.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

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
