package com.dongisarang.partner.place;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log
@Controller
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceDtlRepository placeDtlRepository;

    //@Autowired
    //PlaceService placeService;

    /**
     * 공간 등록페이지로 이동
     * @return
     */
    @GetMapping("/place/registration")
    public String initPlaceRegistForm(){

        Place place = placeRepository.findPlaceByPlaceNo(1);

        if(place != null)
            log.info(""+place);

        // 출력
        List<PlaceDtl> placeDtlList = place.getPlaceDtls();
        if(!placeDtlList.isEmpty())
            placeDtlList.forEach(placeDtl -> {
            System.out.println(placeDtl);
        });


        // 등록
        //PlaceDtl placeDtl = new PlaceDtl(place,"test11", "test11", 22, 22);
        //PlaceDtl placeDt2 = new PlaceDtl(place,"test11", "test11", 22, 22);

        //placeDtlRepository.save(placeDtl);
        //placeDtlRepository.save(placeDt2);
        return "page/place_registration";
    }

    /**
     * 공간 관리 페이지로 이동
     * @return
     */
    @GetMapping("/manage/place")
    public String initManageForm(){
        return "/";
    }



}
