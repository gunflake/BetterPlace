package com.dongisarang.user.place;

import com.dongisarang.user.common.Common;
import com.dongisarang.user.customer.Customer;
import com.dongisarang.user.customer.CustomerRepository;
import com.dongisarang.user.exception.NotFoundCustomerException;
import com.dongisarang.user.exception.NotFoundPlaceDetailException;
import com.dongisarang.user.exception.NotFoundPlaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaceController {

    @Autowired
    private Common common;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceDetailRepository placeDetailRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        Place getPlace = placeRepository.findById(placeNo).orElseThrow(() -> new NotFoundPlaceDetailException("해당 플레이스 정보를 찾을 수 없습니다."));

        List<Comment> comments = commentRepository.findAllByPlace(getPlace);
        for(Comment comment : comments){
            comment.setPrintDate(common.commentDateFormat(comment.getRegisterDate()));
        }


        model.addAttribute("comments", comments);
        model.addAttribute("place", getPlace);

        return "pages/place";
    }

    @GetMapping("/place/test")
    public String processCreationMainForm(Model model){
        //TODO : 추천 공간 3가지마 가지고오기... (어떤 기준으로 3개 가지고 올지 논의하기)
        List<Place> allPlaces = placeRepository.findAll();

        model.addAttribute("allPlaces",  allPlaces);

        return "pages/main :: more_list";
    }

    @GetMapping("/search")
    public String processCreationSearchForm(@RequestParam("keyword") String keyword, Model model){
        List<Place> places = placeRepository.findAllByPlaceNameLike("%" + keyword + "%");
        model.addAttribute("keyword", "\""+keyword+ "\"");
        model.addAttribute("searchPlace", places);
        model.addAttribute("size", places.size());
        return "pages/searchPlace";
    }

    @PostMapping("/place/{placeNo}/comment")
    public String processRegisterComment(@PathVariable("placeNo") int placeNo, @RequestParam String comment, Model model, Principal principal){

        if(principal == null){
            throw new NotFoundCustomerException();
        }

        Place place = placeRepository.findById(placeNo).orElseThrow(() -> new NotFoundPlaceException(String.valueOf(placeNo)));

        Customer currentUser = customerRepository.findByCustomerId(principal.getName()).orElseThrow(() -> new NotFoundCustomerException(principal.getName()));
        Comment newComment = new Comment();
        newComment.setCustomer(currentUser);
        newComment.setPlace(place);
        newComment.setComment(comment);
        Comment save = commentRepository.save(newComment);
        save.setPrintDate(common.commentDateFormat(save.getRegisterDate()));
        model.addAttribute("comments", save);
        return "pages/place :: more_comment";
    }

}
