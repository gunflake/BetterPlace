package com.dongisarang.partner.reservation;

import com.dongisarang.partner.partner.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class ReservationController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * 예약 조횐
     * @return
     */
    @GetMapping("/auth/reservation")
    public String initReservationListForm(){

        Collection<Reservation> reservations = null;

        return "page/reservation";
    }
}
