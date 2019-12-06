package com.dongisarang.partner.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    @GetMapping("/reservation")
    public String initReservationListForm(){

        return "page/reservation";
    }

}
