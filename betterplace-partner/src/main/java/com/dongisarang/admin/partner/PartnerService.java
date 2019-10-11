package com.dongisarang.admin.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;


    public void RegisterPartner(Partner partner) {
        //Log
        System.out.println(partner.getPartnerId());
        System.out.println(partner.getPartnerPassword());

        partnerRepository.save(partner);
    }

    public boolean GetPartner(Partner partner ){
        Partner loginPartner = partnerRepository.findByPartnerIdAndPartnerPassword(partner.getPartnerId(), partner.getPartnerPassword());

        if(loginPartner != null){
            return true;
        }else{
            return false;
        }
    }
}
