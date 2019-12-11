package com.dongisarang.partner.place;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Log
@Controller
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceDtlRepository placeDtlRepository;

    @Autowired
    PlaceService placeService;

    //@Autowired
    //PlaceService placeService;

    /* 공간 등록페이지 */
    @GetMapping("/place/registration")
    public String initPlaceRegistForm(){
        /*
        Place place = placeRepository.findPlaceByPlaceNo(1);

        if(place != null)
            log.info(""+place);

        // 출력

        List<PlaceDtl> placeDtlList = place.getPlaceDtls();
        if(!placeDtlList.isEmpty())
            placeDtlList.forEach(placeDtl -> {
            System.out.println(placeDtl);
        });
            */

        // 등록
        //PlaceDtl placeDtl = new PlaceDtl(place,"test11", "test11", 22, 22);
        //PlaceDtl placeDt2 = new PlaceDtl(place,"test11", "test11", 22, 22);

        //placeDtlRepository.save(placeDtl);
        //placeDtlRepository.save(placeDt2);
        return "page/place_registration";
    }

    /* 공간 등록 후 메인으로 이동한다.*/
    @PostMapping("/place/registration")
    public String processPlaceRegistration(Place place, BindingResult result){
        //TODO: 유효성 추가
        if(result.hasErrors()){
            return "/";
        }else{
            placeService.createPlace(place);
            return "redirect:/";
        }
    }


}
