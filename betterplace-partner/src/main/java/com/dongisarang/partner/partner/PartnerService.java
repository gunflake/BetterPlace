package com.dongisarang.partner.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    /**
     * 파트너 등록
     * @param partner
     */
    public void createPartner(Partner partner) {
        int result = 99 ;

        try{
            partner.setPartnerPassword(passwordEncoder.encode((partner.getPartnerPassword())));
            partnerRepository.save(partner);
//            //findpartner
//
//            int result = 0;
            logger.info("test");
        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("중복아이디 에러");
        }finally {

        }
    }

    /**
     * 파트너 조회
     * @param partnerId
     * @return
     */
    public Partner findPartner(String partnerId){
        return partnerRepository.findByPartnerId(partnerId);
    }

    /**
     * 파트너 비밀번호 변경
     * @param partner
     * @param passwordchange
     */
    public void changePassword(Partner partner, String passwordchange)
    {
        partner.setPartnerPassword(passwordEncoder.encode(passwordchange));
        partnerRepository.save(partner);
    }
}
