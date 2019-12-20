package com.dongisarang.user.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Optional<Place> findByPlaceNameLike(String placeName);
}
