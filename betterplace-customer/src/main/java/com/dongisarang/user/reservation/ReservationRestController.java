package com.dongisarang.user.reservation;

import com.dongisarang.user.place.Place;
import com.dongisarang.user.place.PlaceDetail;
import com.dongisarang.user.place.PlaceDetailRepository;
import com.dongisarang.user.place.PlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceDetailRepository placeDetailRepository;

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

    @GetMapping("/reserve/pg/kakaopay")
    public String getKakaoPaymentPage() {

        StringBuilder builder = null;

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
            map.put("partner_order_id", "partner_order_id");
            map.put("partner_user_id", "betterPlace");
            map.put("item_name", "초코파이");
            map.put("quantity", "1");
            map.put("total_amount", "2200");
            map.put("vat_amount", "200");
            map.put("tax_free_amount", "0");
            map.put("approval_url", "http://localhost:8080/reserved");
            map.put("fail_url", "http://localhost:8080/reserved");
            map.put("cancel_url", "http://localhost:8080/reserved");

            StringBuffer buffer = new StringBuffer();

            //--------------------------
            //   서버에 보낼 데이터 형식 변경 (x-www-form-urlencoded 방식)
            //--------------------------
            Iterator<String> keys = map.keySet().iterator();
            while(keys.hasNext()){
                String tempKey = keys.next();
                buffer.append(tempKey).append("=").append(map.get(tempKey)+"&");
            }

            logger.info("buffer" + buffer.toString());

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

                // TODO : Reader > JSON 데이터이므로 객체로 파싱하기
                builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str + "\n");
                }

                logger.info(builder.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "builder.toString()";

    }

}
