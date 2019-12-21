package com.dongisarang.partner.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class NoticeController {
    @GetMapping("/notice")
    public String index(){
        return "page/notice";
    }
    @GetMapping("/notice2")
    public String index2(){
        return "page/notice2";
    }
}

