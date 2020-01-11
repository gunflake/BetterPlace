package com.dongisarang.partner.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    //Optional<Reservation> findByPertnerId (String partnerId);
    Optional<Reservation> findAllByPartnerId(String partnerId);
}
