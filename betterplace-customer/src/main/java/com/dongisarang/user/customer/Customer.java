package com.dongisarang.user.customer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Integer customerNo; //유저번호

    @Column(length = 20, unique = true)
    private String customerId; //유저아이디

    @Column(length = 255)
    private String customerPassword; //비밀번호

    @Column(length = 20)
    private String nickname; //닉네임

    @Column(length = 13)
    private String phone; //휴대폰

    @Column(length = 50)
    private String email; //이메일

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate; //등록날짜

    @Column
    private Date updateDate; //수정날짜

    @Column
    private Byte state; //상태

    @Column
    private Boolean emailReceptionAgree;

    //비밀번호 변경시 필요한 데이터
    @Transient
    private String changePassword;

    public Customer() {
    }

    public Customer(String customerId, String nickname, String customerPassword, String email) {
        super();
        this.customerId = customerId;
        this.nickname = nickname;
        this.customerPassword = customerPassword;
        this.email = email;
    }
}
