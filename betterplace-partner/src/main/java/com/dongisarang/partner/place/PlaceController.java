package com.dongisarang.partner.place;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log
@Controller
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;
    PlaceDtlRepository placeDtlRepository;

    //@Autowired
    //PlaceService placeService;

    /* 공간 등록페이지 */
    @GetMapping("/place/registration")
    public String initPlaceRegistForm(){

        //Place place = placeRepository.findPlaceByPlaceNo(1);

        //if(place != null)
        //    log.info(""+place);

        //Place place1 = new Place();
        PlaceDtl placeDtl = new PlaceDtl(new Place(),"test11", "test11", 22, 22);
        //PlaceDtl placeDt2 = new PlaceDtl(place1,"test11", "test11", 22, 22);


        placeDtlRepository.save(placeDtl);
        //placeDtlRepository.save(placeDt2);
        return "page/place_registration";
    }




}
