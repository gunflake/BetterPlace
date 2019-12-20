package com.dongisarang.user.dev;

import com.dongisarang.user.partner.Partner;
import com.dongisarang.user.partner.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/*
* 개발용도로 사용하는 사전 데이터
* */
public class DummyData implements ApplicationRunner {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Partner partner = new Partner();
        partner.setPartnerId("partner");
        partner.setEmail("partner@naver.com");
        partner.setPartnerPassword("1234");
        partnerRepository.save(partner);
    }
}
