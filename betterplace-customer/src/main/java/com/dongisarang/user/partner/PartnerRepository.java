package com.betterplace.dev.partner;

import com.betterplace.dev.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Customer, Long> {


}

