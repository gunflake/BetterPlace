package com.betterplace.dev.Partner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {
    Partner findByPartnerIDAndPassword(String id, String password);
}
