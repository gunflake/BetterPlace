package com.dongisarang.user.reservation;

import com.dongisarang.user.customer.Customer;
import com.dongisarang.user.customer.CustomerRepository;
import com.dongisarang.user.exception.NotFoundCustomerException;
import com.dongisarang.user.exception.NotFoundPlaceDetailException;
import com.dongisarang.user.exception.NotFoundPlaceException;
import com.dongisarang.user.kakao.KakaoReady;
import com.dongisarang.user.place.Place;
import com.dongisarang.user.place.PlaceDetail;
import com.dongisarang.user.place.PlaceDetailRepository;
import com.dongisarang.user.place.PlaceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@RestController
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceDetailRepository placeDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationRestController.class);

    /**
     * 공간의 예약 가능한 시간을 구한다.
     * */
    @GetMapping("/reserve/possible/{placeNo}/{placeDetailNo}/{reservationDate}")
    public void getPossibleReservationTime(@PathVariable("placeNo") int placeNo, @PathVariable("placeDetailNo") int placeDetailNo, @PathVariable("reservationDate") String reservationDate){

        Place place = placeRepository.findById(placeNo).orElseThrow();
        PlaceDetail placeDetail = placeDetailRepository.findById(placeDetailNo).orElseThrow();

        List<Reservation> getReservations = reservationRepository.findByPlaceAndPlaceDetailAndReservationDateOrderByStartTime(place, placeDetail, reservationDate);
        Boolean [] possible = new Boolean[24];
        Arrays.fill(possible, true);

        for (Reservation reservation: getReservations) {
            for (int i = reservation.getStartTime().intValue() ; i < reservation.getEndTime().intValue() ; i++){
                possible[i] = false;
            }
        }

        for (int i = 0; i < 24; i++) {
            if(possible[i])
                logger.info("가능시간 :" + i);
        }
    }

    @PostMapping("/reserve/pg/kakaopay")
    public String getKakaoPaymentPage(@RequestBody Reservation reservation) {

        String result = null;
        logger.info(reservation.toString());

        PlaceDetail placeDetail = placeDetailRepository.findById(reservation.getPlaceDetailNo()).orElseThrow(() -> new NotFoundPlaceDetailException(String.valueOf(reservation.getPlaceDetailNo())));
        Place place = placeRepository.findById(reservation.getPlaceNo()).orElseThrow(() -> new NotFoundPlaceException(String.valueOf(reservation.getPlaceNo())));
        Customer customer = customerRepository.findById(reservation.getCustomerNo()).orElseThrow(() -> new NotFoundCustomerException(String.valueOf(reservation.getCustomerNo())));

        reservation.setPlace(place);
        reservation.setPlaceDetail(placeDetail);
        reservation.setCustomer(customer);
        reservation.setState((byte) 3);

        try {
            //--------------------------
            //   서버에 보낼 URL 및 Header 설정
            //--------------------------
            URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 서버에서 읽기 모드 지정
            connection.setDoOutput(true); // 서버로 쓰기 모드 지정

            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setRequestProperty("Authorization", "KakaoAK d0d7afc878a4f8c4b1f61a63c50149cb");
            connection.setRequestMethod("POST");         // 통신방식

            //--------------------------
            //   서버에 보낼 데이터 설정
            //--------------------------
            HashMap<String, String> map = new HashMap<>();
            map.put("cid", "TC0ONETIME");
            map.put("partner_order_id", "betterPlace");
            map.put("partner_user_id", "betterPlace");
            map.put("item_name", "BetterPlace 스터디룸 예약");
            map.put("quantity", "1");
            map.put("total_amount", String.valueOf(reservation.getPrice()));
            map.put("vat_amount", String.valueOf(reservation.getPrice()/10));
            map.put("tax_free_amount", "0");
            map.put("approval_url", "http://localhost:8080/reserved");
            map.put("fail_url", "http://localhost:8080/reserve/fail");
            map.put("cancel_url", "http://localhost:8080/reserve/cancel");

            StringBuffer buffer = new StringBuffer();

            //--------------------------
            //   서버에 보낼 데이터 형식 변경 (x-www-form-urlencoded 방식)
            //--------------------------
            Iterator<String> keys = map.keySet().iterator();
            while(keys.hasNext()){
                String tempKey = keys.next();
                buffer.append(tempKey).append("=").append(map.get(tempKey)+"&");
            }

            OutputStreamWriter outStream = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            //--------------------------
            //   서버로 부터 데이터 받기
            //--------------------------
            logger.info("response code:" + String.valueOf(connection.getResponseCode()));
            logger.info("response message:" + connection.getResponseMessage());

            // 응답 값이 200(성공)이 아닐 경우에 Error 메세지 출력
            if(connection.getResponseCode() != 200){
                InputStream errorStream = connection.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(errorStream));
                String errorMessage = "";
                while( (errorMessage = br.readLine()) != null){
                    logger.error(errorMessage +"\n");
                }
            }else{ // 응답 값이 200일 경우 데이터 받기
                InputStreamReader tmp = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);

                StringBuilder getJsonData = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    getJsonData.append(str + "\n");
                }

                logger.info(getJsonData.toString());

                // JSON Data로 파싱
                ObjectMapper objectMapper = new ObjectMapper();
                KakaoReady kaKaoReady = objectMapper.readValue(getJsonData.toString(), KakaoReady.class);
                logger.info(kaKaoReady.toString());
                result = kaKaoReady.getNext_redirect_pc_url();

                reservation.setTid(kaKaoReady.getTid());
                reservationRepository.save(reservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

}
