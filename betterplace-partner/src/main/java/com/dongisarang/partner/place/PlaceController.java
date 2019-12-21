package com.dongisarang.partner.place;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PlaceController {

    /**
     * 공간 등록페이지로 이동
     * @return
     */
    @GetMapping("/place/registration")
    public String initPlaceRegistForm(){

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
