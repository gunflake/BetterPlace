package com.dongisarang.partner.reservation;

import com.dongisarang.partner.customer.Customer;
import com.dongisarang.partner.place.Place;
import com.dongisarang.partner.place.PlaceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // 사용자가 방금 예약한것중 가장 최근꺼를 가져온다. << 수정 필요할 수 있음...
    Optional<Reservation> findByCustomerOrderByRegisterDateDesc(Customer customer);
    List<Reservation> findByPlaceAndPlaceDetailAndReservationDateOrderByStartTime(Place place, PlaceDetail placeDetail, String reservationDate);
    Optional<Reservation> findAllByPartnerId(String partnerId);
}
