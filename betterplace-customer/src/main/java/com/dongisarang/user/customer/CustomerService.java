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

    public boolean changePassword(String currentPassword, String changePassword) {
        Customer getCustomer = customerRepository.findByCustomerIdAndCustomerPassword("hskim", currentPassword);

        //유저정보가 일치하면
        if(getCustomer !=null){
            getCustomer.setCustomerPassword(changePassword);
            Customer saveConfirm = customerRepository.save(getCustomer);

            if(saveConfirm.getCustomerId().equals("hskim")){
                return true;
            }
        }

        return false;
    }
}
