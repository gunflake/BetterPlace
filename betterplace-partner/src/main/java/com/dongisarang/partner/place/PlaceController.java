package com.dongisarang.partner.place;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 공간 등록페이지로 이동
     * @return
     */
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

    /* 공간 등록 후 세부 공간 등록페이지로 이동한다.*/
            @PostMapping("/place/registration")
            public String processPlaceRegistration(Place place, BindingResult result){
                //TODO: 유효성 추가
                if(result.hasErrors()){
                    return "/";
                }else{
                    int placeno = placeService.createPlace(place);
                    return "redirect:/placeDtl/registration/" + placeno;
        }
    }

    /* 공간 환불 정보 등록 페이지로 이동 */
    @GetMapping("/placerefund/registration")
    public String initPlaceRefundRegistForm(@RequestParam("placeNo") int placeNo){
        return "page/place_refund";
    }

    /* 공간 환불 정보 등록 */
    @PostMapping("/placerefund/registration")
    public String processPlaceRefundRegistration(@RequestParam("placeNo") int placeNo){
            return "page/place_refund";
    }

    /* 세부 공간 등록페이지로 이동 */
    @GetMapping("/placeDtl/registration/{placeNo}")
    public String initPlaceDtlRegistForm(@PathVariable("placeNo") int placeNo){
        return "page/placedtl_registration";
    }

    /* 공간 상세 등록 */
    @PostMapping("/placeDtl/registration/{placeNo}")
    public String processPlaceDtlRegistration(@PathVariable("placeNo") int placeNo, PlaceDtl placedtl, BindingResult result){
        //TODO: 유효성 추가
        log.info("placeNo"+ placeNo);
        if(result.hasErrors()){
            return "/";
        }else{

            Place place = placeRepository.findPlaceByPlaceNo(placeNo);
            PlaceDtl placeDtlAdd = new PlaceDtl(place, placedtl.getPlacedtlname(), placedtl.getPlacedtlintro(), placedtl.getMincount(), placedtl.getMaxcount());

            placeDtlRepository.save(placeDtlAdd);
            return "redirect:/";
        }
    }

}
