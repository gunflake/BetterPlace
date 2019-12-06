package com.dongisarang.partner.partner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {
    Partner findByPartnerId(String partnerId);
}
