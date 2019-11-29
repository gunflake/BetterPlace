package com.dongisarang.partner.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;

@Service
public class MailService {
    // Java 메일
    private JavaMailSender javaMailSender;

    // Thymeleaf Template 사용
    @Autowired
    private TemplateEngine templateEngine;

    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        // Thymeleaf 사용
        MimeMessagePreparator message = mimeMessage -> {

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("admin@betterplace.com");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        };
        javaMailSender.send(message);
    }

    // Thymeleaf 셋팅
    private String build (String text) {
        Context context = new Context();
        context.setVariable("text", text);
        return templateEngine.process("mail-template", context);
    }

}