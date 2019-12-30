package com.dongisarang.partner.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findPlaceByPlaceNo(Integer placeNo);
    Optional<Place> findByPlaceNameLike(String placeName);
}
