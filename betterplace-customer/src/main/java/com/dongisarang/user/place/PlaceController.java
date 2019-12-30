package com.dongisarang.user.place;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    /**
     * Main 페이지를 생성한다.
     * */
    @GetMapping("/")
    public String initCreationMainForm(Model model){
        //TODO : 추천 공간 3가지마 가지고오기... (어떤 기준으로 3개 가지고 올지 논의하기)
        List<Place> allPlaces = placeRepository.findAll();

        List<Place> recommendPlaces = new ArrayList<>();
        for (int i = 0; i < allPlaces.size() && i<3; i++) {
            recommendPlaces.add(allPlaces.get(i));
        }

        model.addAttribute("recommendPlaces", recommendPlaces);
        model.addAttribute("allPlaces",  allPlaces);

        return "pages/main";
    }

    @GetMapping("/place/{placeNo}")
    public String initCreationPlaceForm(@PathVariable("placeNo") int placeNo, Model model){
        // TODO : RestAPI Partner에서 Place 정보 호출...

        //String uri = "http://localhost:8080/partner/getA";
        RestTemplate restTemplate = new RestTemplate();
        //Place getPlace = restTemplate.getForObject(uri, Place.class);

        return "pages/place";
    }

}
