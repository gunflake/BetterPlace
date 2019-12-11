package com.dongisarang.partner.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
    @Autowired
    PlaceRepository placeRepository;
    //public Place findPlace(Integer placeno){
        //return placeRepository.findByPlaceNo(placeno);
    //}

    /* 장소 등록 */
    public void createPlace(Place place) {
        placeRepository.save(place);
    }
}
