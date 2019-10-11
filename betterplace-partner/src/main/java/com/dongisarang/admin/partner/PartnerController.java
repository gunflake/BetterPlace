package com.dongisarang.admin.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PartnerController {

    @Autowired
    PartnerService partnerService;

    @RequestMapping("/")
    public String init(){
        return "index";
    }

    @RequestMapping("/signupForm")
    public String GoToSignupPage(){
        //회원가입 페이지
        return "signup";
    }

    @RequestMapping("/loginForm")
    public String GoToLoginPage(){
        //로그인페이지
        return "login";
    }

    @RequestMapping("/signup")
    public String RegisterPartner(Partner partner){
        //파트너 등록 후 로그인페이지로 이동
        partnerService.RegisterPartner(partner);
        return "login";
    }

    @RequestMapping("/login")
    public String Login(Partner partner){

        boolean isPartner =  partnerService.GetPartner(partner);
        //Log
        System.out.println(isPartner);

        //파트너이면 index페이지로, 아니면 error페이지(임시)로 리다이렉트
        if(isPartner){
            return "index";
        }else{
            return "error";
        }

    }
}

