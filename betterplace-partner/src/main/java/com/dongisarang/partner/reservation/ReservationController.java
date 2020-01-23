package com.dongisarang.partner.reservation;

import com.dongisarang.partner.customer.Customer;
import com.dongisarang.partner.partner.Partner;
import com.dongisarang.partner.partner.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;/**/

import java.security.Principal;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    PartnerService partnerService;

    @Autowired
    ReservationRepository reservationRepository;

    /**
     * 예약 관리 리스트 페이지로 이동
     * @return
     */
    @GetMapping("/auth/reservation")
    public String initReservationListForm(){
        return "page/reservation";
    }

    /**
     * 예약 리스트 조회
     * @return
     */
    @PostMapping("/auth/reservation")
    public List<Reservation> processReservationListForm(Principal principal, BindingResult result, @RequestParam(required = false) Integer reservationNo, @RequestParam(required = false) Customer customer){

        List<Reservation> reservations = null;

        Partner partner = partnerService.findPartner(principal.getName());

        if(partner == null){

        }
        if(reservationNo != null) {
            return reservationRepository.findByPartnerNoAnAndReservationNo(principal., reservationNo);
        } else if (customer != null){
            return reservationRepository.findAllByCustomerId(customer.getCustomerId());
        }else {
            return reservationRepository.findAllByPartnerNo(partner.getPartnerNo());
        }
    }
}
