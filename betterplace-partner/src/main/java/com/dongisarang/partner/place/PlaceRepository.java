package com.dongisarang.partner.place;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findPlaceByPlaceNo(Integer placeNo);
}