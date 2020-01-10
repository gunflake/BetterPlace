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

import java.util.Arrays;
import java.util.List;

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

}
