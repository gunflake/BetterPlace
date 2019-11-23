package com.dongisarang.admin.common;

import com.dongisarang.admin.partner.Partner;
import com.dongisarang.admin.partner.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Partner partner = new Partner();
        partner.setPartnerId("gunflake");
        partner.setPartnerPassword(bCryptPasswordEncoder.encode("1234"));
        partnerRepository.save(partner);

    }
}
