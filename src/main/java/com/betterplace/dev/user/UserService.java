package com.betterplace.dev.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserService {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/loginform")
    public String loginPage(){
        // id / pw 받으면 Db조회에서 값이 일치하면
        // login 페이지로 이동

        //
        return "Login";
    }

    @RequestMapping(value = "/user/signupform")
    public String signUpPage(){
        //데이터받아서 user테이블에 데이터 넣어주기
        return "SignUp";
    }

    @RequestMapping(value = "/user/signup")
    public String signUpUser(User user){

        userRepository.save(user);

        return "Login";
    }

    @RequestMapping(value = "/user/login")
    public String loginUser(HttpServletRequest request){
        String id = request.getParameter("ID" );
        String password = request.getParameter("Password" );

        User getUser = userRepository.findByUserIDAndPassword(id, password);

        if(getUser != null) {
            return "Home";
        } else {
            return "Login";
        }
    }
}
