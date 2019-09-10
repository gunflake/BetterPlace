package com.betterplace.dev.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    List<Customer> findByPhone(String phone);
    List<Customer> findByNameLike(String name);

}

