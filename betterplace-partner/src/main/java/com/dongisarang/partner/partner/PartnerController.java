package com.dongisarang.partner.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PartnerController {

    @Autowired
    PartnerService partnerService;

    /* main 페이지로 이동한다 */
    @GetMapping("/")
    public String index(){
        return "page/main";
    }

    /* 로그인 페이지로 이동한다 */
    @GetMapping("/auth/login")
    public String initLoginForm(){
        return "page/login";
    }

    /* 파트너 회원가입 페이지로 이동한다 */
    @GetMapping("/auth/signup")
    public String initSignupForm(){
        return "page/signup";
    }

    /* 파트너 등록 후 로그인 페이지로 이동한다 */
    @PostMapping("/auth/signup")
    public String processSignupForm(@Valid Partner partner, BindingResult result){
        //TODO: 유효성 추가
        if(result.hasErrors()){
            return "/";
        }else{
            partnerService.createPartner(partner);
            return "redirect:/auth/login";
        }
    }

    //TODO: 비밀번호 찾기 시 이메일 서비스 추가


    /* 파트너 마이페이지 */
    @GetMapping("/mypage")
    public String initMypage(Model model, Principal principal){

        Partner partner = partnerService.findPartner(principal.getName());

        if(partner != null)
            model.addAttribute("partner", partner);

        return "page/mypage";
    }

    @GetMapping("/mypage/changepassword")
    public String initPasswordForm(){
        return "page/changepassword";
    }


    @PostMapping(value="/join")
    public String setJoin() {
        return "redirect:/login";
    }

}

