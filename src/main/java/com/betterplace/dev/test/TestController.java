package com.betterplace.dev.test;

import com.betterplace.dev.payment.Payment;
import com.betterplace.dev.payment.PaymentRepository;
import com.betterplace.dev.pg.PG;
import com.betterplace.dev.pg.PGRepository;
import com.betterplace.dev.reserve.ReservationRepository;
import com.betterplace.dev.user.User;
import com.betterplace.dev.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PGRepository pgRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/")
    public String home(){
        User user = new User("gunflake", "vincent", "1234", "gunflake09@naver.com");

        PG pg = new PG();
        pg.setPgName("네이버 페이");

        Payment payment = new Payment();
        payment.setPrice(1000.0);
        payment.setTid("ABDJSJFK12141");
        payment.setUser(user);
        payment.setPg(pg);
//
//        Reservation reservation = new Reservation();
//        reservation.setTotalPrice(10000.0);
//        reservation.setRealPrice(9000.0);
//        reservation.setCuponPrice(1000.0);
//        reservation.setPayment(payment);
//        reservation.setUser(user);

//        pg.addPayment(payment);
//        user.addPayment(payment);

        userRepository.save(user);
        pgRepository.save(pg);
        paymentRepository.save(payment);


        Payment one = paymentRepository.getOne(3);
//        reservationRepository.save(reservation);
        System.out.println(one.toString());
        return "Home";
    }


}
