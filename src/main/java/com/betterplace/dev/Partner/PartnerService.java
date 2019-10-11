package com.betterplace.dev.Partner;

import com.betterplace.dev.SendMail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        return "Partner_Login";
    }

    private EmailService emailService;

    private void sendEmailTest() {
        String to = "gkslwhs@naver.com";
        String subject = "이메일 테스트";
        String text = "메일 내용";

        emailService.sendSimpleMessage(to,subject,text);
    }

    @RequestMapping(value = "/partner/signupform")
    public String signUpPage(){
        //데이터받아서 Parter테이블에 데이터 넣어주기
        return "Partner_SignUp";
    }

    @RequestMapping(value = "/partner/signup")
    public String signUpPartner(Partner partner){
        partnerRepository.save(partner);

        return "Partner_Login";
    }

    @RequestMapping(value = "/partner/login")
    public String loginPartner(HttpServletRequest request){
        String id = request.getParameter("ID" );
        String password = request.getParameter("Password" );

        Partner getPartner = partnerRepository.findByPartnerIDAndPassword(id, password);

        if(getPartner != null) {
            return "Home";
        } else {
            return "Partner_Login";
        }
    }

    @RequestMapping(value = "/partner/findByID")
    public String findID(Partner partner){
        return "Partner_FindID";
    }

    @RequestMapping(value = "/partner/resultID")
    public String resultFindID(HttpServletRequest request, Model model){
        String email = request.getParameter("Email" );

        Partner getPartner = partnerRepository.findPartnerByEmail(email);

        if(getPartner != null) {
            model.addAttribute("id", getPartner.getPartnerID());

            return "Partner_ResultID";
        } else {
            return "Home";
        }

    }
}
