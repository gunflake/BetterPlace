package com.dongisarang.user.partner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository partnerRepository;

    @BeforeEach
    void savePartner() {
        Partner partner = new Partner();
        partner.setPartnerId("partner");
        partner.setEmail("partner@naver.com");
        partner.setPartnerPassword("1234");
        partnerRepository.save(partner);
    }

    @Test
    void searchPartner() throws Exception{
        Optional<Partner> partner = partnerRepository.findByPartnerId("partner");
        assertThat(partner.get().getPartnerId()).isEqualTo("partner");
    }

}
