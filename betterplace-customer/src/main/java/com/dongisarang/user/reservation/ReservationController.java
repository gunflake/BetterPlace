package com.dongisarang.user.reservation;

import com.dongisarang.user.customer.Customer;
import com.dongisarang.user.customer.CustomerRepository;
import com.dongisarang.user.exception.NotFoundCustomerException;
import com.dongisarang.user.exception.NotFoundPlaceDetailException;
import com.dongisarang.user.exception.NotFoundPlaceException;
import com.dongisarang.user.exception.NotFoundReservationException;
import com.dongisarang.user.kakao.KakaoApprove;
import com.dongisarang.user.kakao.KakaoError;
import com.dongisarang.user.place.Place;
import com.dongisarang.user.place.PlaceDetail;
import com.dongisarang.user.place.PlaceDetailRepository;
import com.dongisarang.user.place.PlaceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

@Controller
public class ReservationController {

    @Autowired
    private CustomerRepository customerRepository;

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
                                          Principal principal,
                                          Model model){
        //Place 정보 받아서 넘기기
        Place place = placeRepository.findById(Integer.parseInt(placeNo)).orElseThrow(() -> new NotFoundPlaceException("플레이스 정보를 찾을 수 없습니다."));
        //Todo : Place 예약 시작 시간 및 예약 날짜가 유효한지 검증로직 추가하기

        //PlaceDtl 정보 (plateDtl No 받아서)
        PlaceDetail placeDetail = placeDetailRepository.findById(Integer.parseInt(placeDetailNo)).orElseThrow(() -> new NotFoundPlaceDetailException("플레이스 세부 정보를 찾을 수 없습니다."));
        //Todo : PlaceDetail 예약 사용시간이 유효한지, 최소 인원 <= 예약 인원 <= 최대 인원 안에 들어가는지 확인하기

        //예약 날짜, 예약 시작 시간, 예약 사용 시간, 예약 인원

        Customer currentUser = customerRepository.findByCustomerId(principal.getName()).orElseThrow(() -> new NotFoundCustomerException(principal.getName()));

        int endTime = Integer.parseInt(startTime) + Integer.parseInt(useTime);

        int totalPrice = Integer.parseInt(useTime) * place.getDefaultPrice() * Integer.parseInt(count);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 (E) ");
        String reserveDate = "";
        String reserveTime = startTime + "시 ~ " + endTime +"시";
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
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("reservationDate", reservationDate);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);


        return "pages/reserve";
    }

    @GetMapping("/reserved")
    public String goReserved(@RequestParam(value = "pg_token") String pgToken, Principal principal, Model model){

        Customer currentUser = customerRepository.findByCustomerId(principal.getName()).orElseThrow(() -> new NotFoundCustomerException(principal.getName()));
        Reservation reservation = reservationRepository.findTop1ByCustomerOrderByReservationNoDesc(currentUser).orElseThrow(() -> new NotFoundReservationException(principal.getName()));

        logger.info("PG TOKEN = "+pgToken);

        // Kakao Approve 승인 URL 호출
        try{
            //--------------------------
            //   서버에 보낼 URL 및 Header 설정
            //--------------------------
            URL url = new URL("https://kapi.kakao.com/v1/payment/approve");
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
            map.put("tid", reservation.getTid());
            map.put("pg_token", pgToken);

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

                StringBuilder getJsonData = new StringBuilder();
                String str;
                while ((str = br.readLine()) != null) {
                    getJsonData.append(str + "\n");
                }

                ObjectMapper objectMapper = new ObjectMapper();
                KakaoError kakaoError = objectMapper.readValue(getJsonData.toString(), KakaoError.class);

                logger.error("code = "+kakaoError.getCode());

                if(kakaoError.getCode() == -702){
                    setReservedModelAttribute(model, reservation);
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
                KakaoApprove kakaoApprove = objectMapper.readValue(getJsonData.toString(), KakaoApprove.class);
                logger.info(kakaoApprove.toString());


                // 예약 공간 이름
                // 예약 세부공간 이름
                // 예약 날짜 2019.11.25 (월)'
                // 예약 시간 10시 ~ 13시 (3시간)
                // 예약 인원 3명
                // 요청 사항 없음
                // 결제 금액 총 금액



                setReservedModelAttribute(model, reservation);



            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "pages/reserved";
    }

    private void setReservedModelAttribute(Model model, Reservation reservation) {
        // 시간 Format 변경
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 (E) ");
        String reserveDate = "";
        String reserveTime = reservation.getStartTime() + "시 ~ " + reservation.getEndTime() +"시";
        try{
            Date date = simpleDateFormat.parse(reservation.getReservationDate());
            reserveDate = outputDateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        model.addAttribute("placeName", reservation.getPlace().getPlaceName());
        model.addAttribute("placeDetailName", reservation.getPlaceDetail().getPlaceDetailName());
        model.addAttribute("reserveDate", reserveDate);
        model.addAttribute("reserveTime", reserveTime);
        model.addAttribute("reserveCount", reservation.getCustomerCount());
        //Todo : message 받아서 넣기
        model.addAttribute("message", "빔 프로젝트 준비해주세요.");
        model.addAttribute("totalPrice", reservation.getPrice());
    }


}
