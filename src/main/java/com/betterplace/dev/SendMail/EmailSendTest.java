package com.betterplace.dev.SendMail;

import org.springframework.beans.factory.annotation.Autowired;

public class EmailSendTest {

    // 이메일 발송
    @Autowired
    private EmailService emailService;


    public void sendEmail() {
        String to = "gkslwhs@naver.com";
        String subject = "이메일 테스트";
        String text = "메일 내용";

        emailService.sendSimpleMessage(to,subject,text);
    }
}
