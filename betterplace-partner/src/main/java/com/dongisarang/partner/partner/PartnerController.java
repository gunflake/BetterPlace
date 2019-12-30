package com.dongisarang.partner.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class PartnerController {

    @Autowired
    PartnerService partnerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger LOG = LoggerFactory.getLogger(PartnerController.class);

    /**
     * 메인 페이지로 이동
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "page/index";
    }

    /**
     * 로그인 페이지로 이동
     * @return
     */
    @GetMapping("/auth/login")
    public String initLoginForm(){
        return "page/login";
    }

    /**
     * 파트너 회원가입 페이지로 이동
     * @return
     */
    @GetMapping("/auth/signup")
    public String initSignupForm(){
        return "page/signup";
    }

    /**
     * 파트너 등록 후 로그인 페이지로 이동
     * @param partner
     * @param result
     * @return
     */
    @PostMapping("/auth/signup")
    public String processSignupForm(@Valid Partner partner, BindingResult result){
        //TODO: 유효성 추가
        if(result.hasErrors()){
            LOG.error("회원 정보가 유효하지 않습니다.");
            List<ObjectError> errorLists = result.getAllErrors();
            for (ObjectError error : errorLists) {
                LOG.error(error.toString());
            }
            return "redirect:/auth/signup";
        }else{
            partnerService.createPartner(partner);
            return "redirect:/auth/login";
        }
    }

    //TODO: 비밀번호 찾기 시 이메일 서비스 추가


    /**
     * 파트너 마이페이지로 이동
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/mypage")
    public String initMypage(Model model, Principal principal){

        Partner partner = partnerService.findPartner(principal.getName());

        if(partner != null)
            model.addAttribute("partner", partner);

        return "page/mypage";
    }

    /**
     * 파트너 비밀번호 변경 페이지로 이동
     * @return
     */
    @GetMapping("/mypage/changepassword")
    public String initPasswordForm(){

        return "page/changepassword";
    }

    /**
     * 파트너 비밀번호 변경 로직
     * @param partnerPassword 기존 비밀번호
     * @param changePassword  새 비밀번호
     * @param principal
     * @return
     */
    @PostMapping("/mypage/changepassword")
    public String processPasswordForm(String partnerPassword, String changePassword, Principal principal){

        Partner partner = partnerService.findPartner(principal.getName());

        // 현재 비밀번호 확인
        if(!passwordEncoder.matches(partnerPassword,partner.getPartnerPassword()))
        {
            return "redirect:/mypage";
        }

        // 새 비밀번호 등록
        partnerService.changePassword(partner,changePassword);

        return "redirect:/mypage";
    }

}

