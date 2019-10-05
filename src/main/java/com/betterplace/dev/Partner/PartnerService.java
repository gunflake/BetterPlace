package com.betterplace.dev.Partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    @RequestMapping(value = "/partner/loginform")
    public String loginPage(){
        // id / pw 받으면 Db조회에서 값이 일치하면
        // login 페이지로 이동

        //
        return "Login";
    }

    @RequestMapping(value = "/partner/signupform")
    public String signUpPage(){
        //데이터받아서 user테이블에 데이터 넣어주기
        return "SignUp";
    }

    @RequestMapping(value = "/partner/signup")
    public String signUpUser(Partner partner){
        partnerRepository.save(partner);

        return "Login";
    }

    @RequestMapping(value = "/partner/login")
    public String loginUser(HttpServletRequest request){
        String id = request.getParameter("ID" );
        String password = request.getParameter("Password" );

        Partner getUser = partnerRepository.findByPartnerIDAndPassword(id, password);

        if(getUser != null) {
            return "Home";
        } else {
            return "Login";
        }
    }
}
