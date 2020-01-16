package com.dongisarang.user.place;

import com.dongisarang.user.exception.NotFoundPlaceDetailException;
import com.dongisarang.user.exception.NotFoundPlaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceDetailRepository placeDetailRepository;

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    /**
     * Main 페이지를 생성한다.
     * */
    @GetMapping("/")
    public String initCreationMainForm(Model model){
        //TODO : 추천 공간 3가지마 가지고오기... (어떤 기준으로 3개 가지고 올지 논의하기)
        List<Place> allPlaces = placeRepository.findAll();

        List<Place> recommendPlaces = new ArrayList<>();
        for (int i = 0; i < allPlaces.size() && i<3; i++) {
            recommendPlaces.add(allPlaces.get(i));
        }

        model.addAttribute("recommendPlaces", recommendPlaces);
        model.addAttribute("allPlaces",  allPlaces);

        return "pages/main";
    }

    @GetMapping("/place/{placeNo}")
    public String initCreationPlaceForm(@PathVariable("placeNo") int placeNo, Model model){
        Place getPlace = placeRepository.findById(placeNo).orElseThrow(() -> new NotFoundPlaceDetailException("해당 플레이스 정보를 찾을 수 없습니다."));

        model.addAttribute("place", getPlace);

        return "pages/place";
    }

    @PostMapping("/place/{placeNo}")
    public String processCreationReserveForm(@PathVariable("placeNo") int placeNo,
                                             @RequestParam String placeDetailNo,
                                             @RequestParam String reservationDate, // 예약 날짜
                                             @RequestParam String startTime,  // 예약 시작 시간
                                             @RequestParam String useTime,    // 예약 사용 시간 (3시간 등)
                                             @RequestParam String count,     // 예약 인원
                                             Model model){
        //Place 정보 받아서 넘기기
        Place place = placeRepository.findById(placeNo).orElseThrow(() -> new NotFoundPlaceException("플레이스 정보를 찾을 수 없습니다."));
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

        // 2019.11.25 (월) 10시 ~ 13시
//        int year = Integer.parseInt(reservationDate.substring(0,4));
//        int month = Integer.parseInt(reservationDate.substring(4,6));
//        int day = Integer.parseInt(reservationDate.substring(6,8));
        //String date = reservationDate.substring(0,4) + '.' + reservationDate.substring(4,6) + '.' + reservationDate.substring(6,8);
        //Date date1 = new Date(year, month, day);

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

    @GetMapping("/place/test")
    public String processCreationMainForm(Model model){
        //TODO : 추천 공간 3가지마 가지고오기... (어떤 기준으로 3개 가지고 올지 논의하기)
        List<Place> allPlaces = placeRepository.findAll();

        model.addAttribute("allPlaces",  allPlaces);

        return "pages/main :: more_list";
    }

}
