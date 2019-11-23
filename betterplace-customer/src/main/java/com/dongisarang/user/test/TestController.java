package com.dongisarang.user.test;

import com.dongisarang.common.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        TestService testService = new TestService();
        return testService.hello();
    }


}
