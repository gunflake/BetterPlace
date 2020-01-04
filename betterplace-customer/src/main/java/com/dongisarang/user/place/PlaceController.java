package com.dongisarang.user.place;

import com.dongisarang.user.exception.NotFoundPlaceDtlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        Place getPlace = placeRepository.findById(placeNo).orElseThrow(() -> new NotFoundPlaceDtlException("해당 플레이스 정보를 찾을 수 없습니다."));

        //공간 제목 가져오기
        String placeName = getPlace.getPlaceName();

        //공간 테그 가져오기
        String tags = getPlace.getTag();

        //공간 이미지 가져오기
        String imgSrc = getPlace.getImage();

        //공간 시설 안내 가져오기
        String[] information = getPlace.getInfo().split(";");

        //예약 시 주의사항 내용 DB에서 가져오기
        String[] notice = getPlace.getNotice().split(";");

        //공간 소개 가져오기
        String introduction = getPlace.getIntro();

        model.addAttribute("place", getPlace);

        return "pages/place";
    }

}
