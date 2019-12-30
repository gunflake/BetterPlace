package com.dongisarang.partner.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/place")
public class PlaceRestController {

    @Autowired
    private PlaceRepository placeRepository;

    /* 사용자에게 Place 추천 공간 정보 제공 */
    @GetMapping("/recommend")
    public List<Place> getRecommendPlaces(){
        //TODO : 추천 공간 3가지마 가지고오기... (어떤 기준으로 3개 가지고 올지 논의하기)
        List<Place> allPlaces = placeRepository.findAll();

        List<Place> recommendPlaces = new ArrayList<>();
        for (int i = 0; i < allPlaces.size() && i<3; i++) {
            recommendPlaces.add(allPlaces.get(i));
        }

        return recommendPlaces;
    }
}
