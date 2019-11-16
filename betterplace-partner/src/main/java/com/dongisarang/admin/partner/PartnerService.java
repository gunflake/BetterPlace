package com.dongisarang.admin.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /* 파트너 등록 */
    public void createPartner(Partner partner) {
        partner.setPartnerPassword(passwordEncoder.encode((partner.getPartnerPassword())));
        partnerRepository.save(partner);
    }

    /* 파트너 조회 */
    public Partner findPartner(String partnerId){
        return partnerRepository.findByPartnerId(partnerId);
    }

    /* 비밀번호 변경 */
    public void changePassword(Partner partner, String passwordchange)
    {
        partner.setPartnerPassword(passwordEncoder.encode(passwordchange));
        partnerRepository.save(partner);
    }
}
