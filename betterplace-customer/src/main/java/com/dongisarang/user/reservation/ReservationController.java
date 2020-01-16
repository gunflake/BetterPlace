package com.dongisarang.user.reservation;

import com.dongisarang.user.exception.NotFoundPlaceDetailException;
import com.dongisarang.user.exception.NotFoundPlaceException;
import com.dongisarang.user.place.Place;
import com.dongisarang.user.place.PlaceDetail;
import com.dongisarang.user.place.PlaceDetailRepository;
import com.dongisarang.user.place.PlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceDetailRepository placeDetailRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @PostMapping("/reserve")
    public String initCreationReserveForm(@RequestParam String placeNo,
                                          @RequestParam String placeDetailNo,
                                          @RequestParam String reservationDate, // 예약 날짜
                                          @RequestParam String startTime,  // 예약 시작 시간
                                          @RequestParam String useTime,    // 예약 사용 시간 (3시간 등)
                                          @RequestParam String count,     // 예약 인원
                                          Model model){
        //Place 정보 받아서 넘기기
        Place place = placeRepository.findById(Integer.parseInt(placeNo)).orElseThrow(() -> new NotFoundPlaceException("플레이스 정보를 찾을 수 없습니다."));
        //Todo : Place 예약 시작 시간 및 예약 날짜가 유효한지 검증로직 추가하기

        //PlaceDtl 정보 (plateDtl No 받아서)
        PlaceDetail placeDetail = placeDetailRepository.findById(Integer.parseInt(placeDetailNo)).orElseThrow(() -> new NotFoundPlaceDetailException("플레이스 세부 정보를 찾을 수 없습니다."));
        //Todo : PlaceDetail 예약 사용시간이 유효한지, 최소 인원 <= 예약 인원 <= 최대 인원 안에 들어가는지 확인하기

        //예약 날짜, 예약 시작 시간, 예약 사용 시간, 예약 인원

        //RequestParma 정보 제대로 가지고오는지 로그 찍어서 확인
        logger.info(placeDetailNo);
        logger.info(reservationDate);
        logger.info(startTime);
        logger.info(useTime);
        logger.info(count);

        int endTime = Integer.parseInt(startTime) + Integer.parseInt(useTime);

        int totalPrice = Integer.parseInt(useTime) * place.getDefaultPrice() * Integer.parseInt(count);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 (E) ");
        String reserveDate = "";
        String reserveTime = startTime + "시 ~ " + endTime +"시";
        count += "명";
        try{
            Date date = simpleDateFormat.parse(reservationDate);
            reserveDate = outputDateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        model.addAttribute("place", place);
        model.addAttribute("placeDetail", placeDetail);
        model.addAttribute("reserveDate", reserveDate);
        model.addAttribute("reserveTime", reserveTime);
        model.addAttribute("useTime", useTime);
        model.addAttribute("count", count);
        model.addAttribute("totalPrice", totalPrice);


        return "pages/reserve";
    }
}
