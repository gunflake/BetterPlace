package com.dongisarang.admin.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    //TODO: Security 사용 예정
    @RequestMapping("/login")
    public String Login(Partner partner, HttpSession session){
        Partner loginPartner =  partnerService.GetPartner(partner);

        if( loginPartner == null) {
            return "redirect:/loginform";
        }

        if( !partner.getPartnerPassword().equals(loginPartner.getPartnerPassword())){
            return "redirect:/loginform";
        }

        //TODO: sessionPartner 또는 partnerSession으로 변경하여 충돌 방지
        session.setAttribute("partnerId", loginPartner.getPartnerId());

        //Log
        System.out.println("LOGIN SUCCEESS [id: " + loginPartner.getPartnerId()
                + " /sessioned Id: " + session.getAttribute("partnerId") + "]");

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String Logout(HttpSession session){
        session.removeAttribute("partnerId");
        //LOG
        System.out.println("LOGOUT SUCCESS" );
        System.out.println(session.getAttribute("partnerId");
        return "redirect:/";
    }
}

