package com.dongisarang.partner.partner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class PartnerRepositoryTest {

    @Autowired
    PartnerRepository partnerRepository;

    @BeforeEach
    public void test(){
        //partner
    }

    /***
     * PartnerRepository Test
     * @throws Exception
     */
    @Test
    public void savePartner() throws Exception{
        Partner partner = new Partner();
        partner.setPartnerId("hjhwang");
        partnerRepository.save(partner);

        //Partner p = partnerRepository.findByPartnerId("hjhwang");
        //assertThat(p.getPartnerId()).isEqualTo("hjhwang");
    }


}