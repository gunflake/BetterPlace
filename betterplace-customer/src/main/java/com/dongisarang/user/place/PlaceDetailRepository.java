package com.dongisarang.user.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceDetailRepository extends JpaRepository<PlaceDetail, Integer> {
    Optional<PlaceDetail> findByPlaceAndPlaceDetailName(Place place, String placeDetailName);
}
