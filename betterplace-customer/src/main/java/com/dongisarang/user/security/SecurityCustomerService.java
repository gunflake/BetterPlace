package com.dongisarang.user.security;

import com.dongisarang.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//@Log
public class SecurityCustomerService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int customerNo = customerRepository.findByCustomerId(username).getCustomerNo();

        return customerRepository.findById(customerNo)
                .filter(c -> c != null)
                .map(c -> new SecurityCustomer(c)).get();
    }
}
