package com.dongisarang.partner.dev;

import com.dongisarang.partner.partner.Partner;
import com.dongisarang.partner.partner.PartnerRepository;
import com.dongisarang.partner.partner.PartnerService;
import com.dongisarang.partner.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class DummyData implements ApplicationRunner {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PartnerService partnerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        savePartner();
        //savePlace();
    }

    private void savePartner(){
        Partner partner1 = new Partner();
        partner1.setPartnerId("gunflake09");
        partner1.setNickname("gunflake09");
        partner1.setPartnerPassword("1234");
        partnerService.createPartner(partner1);
    }
    private void savePlace() {

    }

}
