package com.dongisarang.partner.partner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {
    Optional<Partner> findByPartnerId(String partnerId);
}
