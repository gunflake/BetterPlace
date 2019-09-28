package com.betterplace.dev.user;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {

    @GetMapping("/login")
    public void login(@RequestBody User user){
        // id / pw 받으면 Db조회에서 값이 일치하면
        // login 페이지로 이동

        //

    }


    public void signUp(){

        //데이터받아서 user테이블에 데이터 넣어주기

    }
}
