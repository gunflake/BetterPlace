package com.dongisarang.partner.place;

import com.dongisarang.partner.exception.InvalidImageException;
import com.dongisarang.partner.partner.Partner;
import com.dongisarang.partner.partner.PartnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceDetailRepository placeDetailRepository;

    @Autowired
    PlaceInfoRepository placeInfoRepository;

    @Autowired
    PlaceService placeService;

    @Autowired
    PartnerService partnerService;

    private static final Logger log = LoggerFactory.getLogger(PlaceController.class);

    @GetMapping("/place/registration")
    public String initPlaceRegistForm(){
        return "page/place_registration";
    }

    /* 공간 등록 후 세부 공간 등록페이지로 이동한다.*/
    @PostMapping("/place/registration")
    public String processPlaceRegistration(Place place, BindingResult result, Principal principal, @RequestParam("imageFile") MultipartFile multipartFile) {

        //TODO: 유효성 추가
        if(result.hasErrors()){
            return "/";
        }else{

            if(multipartFile == null) {
                return "fail upload";
            }
            // TAG
            ArrayList<String> tags = place.getTags();
            String joinTag = "";

            for (String tag: tags
                 ) {
                joinTag+=tag+";";
            }
            place.setTag(joinTag.substring(0, joinTag.length()-1));

            // INFO
            ArrayList<String> infos = place.getInfos();
            String infoTag = "";

            for (String info: infos
            ) {
                infoTag+=info+";";
            }
            place.setInfo(infoTag.substring(0, infoTag.length()-1));


            // NOTICE
            ArrayList<String> notices = place.getNotices();
            String noticeTag = "";

            for (String notice: notices
            ) {
                noticeTag+=notice+";";
            }
            place.setNotice(noticeTag.substring(0, noticeTag.length()-1));

            // PartnerNo
            Partner partner =  partnerService.findPartner(principal.getName());
            place.setPartner(partner);


            log.info(multipartFile.getOriginalFilename());

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            Date date = cal.getTime();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss");

            // 이미지 파일 등록
            Path fileNameAndPath = Paths.get("./images/",multipartFile.getOriginalFilename().substring(0,multipartFile.getOriginalFilename().indexOf('.')) + format1.format((date)) + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf('.'), multipartFile.getOriginalFilename().length()) );
            try{
                Files.write(fileNameAndPath, multipartFile.getBytes());
            }catch (IOException e){
                throw new InvalidImageException("이미지 업로드에 실패했습니다.");
            }

            place.setImage(fileNameAndPath.toString());
            // 공간 등록하기
            int placeno = placeService.createPlace(place);
            return "redirect:/placeinfo/registration/" + placeno;
        }
    }

    /* 공간 정보 등록 페이지로 이동  */
    @GetMapping("/placeinfo/registration/{placeNo}")
    public String initPlaceInfoRegistForm(@PathVariable("placeNo") int placeNo) {
        return "page/placeinfo_registration";
    }

    /* 공간 정보 등록 */
    @PostMapping("/placeinfo/registration/{placeNo}")
    public String processPlaceInfoRegistration(@PathVariable("placeNo") int placeNo, PlaceInfo placeInfo, BindingResult result){
        //TODO: 유효성 추가
        if(result.hasErrors()){
            return "/";
        }else{

            Place place = placeRepository.findPlaceByPlaceNo(placeNo);
            PlaceInfo placeInfoAdd = new PlaceInfo(place, placeInfo.getDay(), placeInfo.getStarttime(), placeInfo.getEndtime());

            placeInfoRepository.save(placeInfoAdd);
            return "redirect:/placeDtl/registration/" + placeNo;
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
    public String processPlaceDtlRegistration(@PathVariable("placeNo") int placeNo, PlaceDetail placedtl, BindingResult result){
        //TODO: 유효성 추가
        log.info("placeNo"+ placeNo);
        if(result.hasErrors()){
            return "/";
        }else{

            Place place = placeRepository.findPlaceByPlaceNo(placeNo);
            PlaceDetail placeDetailAdd = new PlaceDetail(place, placedtl.getPlaceDetailName(), placedtl.getPlaceDetailIntro(), placedtl.getMinCount(), placedtl.getMaxCount());

            placeDetailRepository.save(placeDetailAdd);
            return "redirect:/";
        }
    }

}
