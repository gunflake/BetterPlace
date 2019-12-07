package com.dongisarang.partner.place;

import org.springframework.beans.factory.annotation.Autowired;

public class PlaceService {
    @Autowired
    PlaceRepository placeRepository;
    //public Place findPlace(Integer placeno){
        //return placeRepository.findByPlaceNo(placeno);
    //}
}
