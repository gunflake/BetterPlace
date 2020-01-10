package com.dongisarang.partner.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    /**
     * 예약 관리 리스트 페이지로 이동
     * @return
     */
    @GetMapping("/auth/reservation")
    public String initReservationListForm(){
        return "page/reservation";
    }


}
