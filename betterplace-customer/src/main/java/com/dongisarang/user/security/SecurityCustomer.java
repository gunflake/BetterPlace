package com.dongisarang.user.security;

import com.dongisarang.user.customer.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SecurityCustomer extends User {

    private Customer customer;

    public SecurityCustomer(Customer customer) {
        super(customer.getCustomerId(), customer.getCustomerPassword(), makeGrantedAuthority());
        this.customer = customer;
    }

    private static List<GrantedAuthority> makeGrantedAuthority() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

        return list;
    }
}
