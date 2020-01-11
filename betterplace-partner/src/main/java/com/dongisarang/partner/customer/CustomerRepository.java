package com.dongisarang.partner.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByCustomerIdAndCustomerPassword(String customerId, String customerPassword);
    Customer findByCustomerId(String customerId);

}
