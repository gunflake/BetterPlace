package com.dongisarang.user.customer;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer customerNo; //유저번호

    @Column(length = 20, unique = true)
    private String customerId; //유저아이디

    @Column(length = 60)
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


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Boolean getEmailReceptionAgree() {
        return emailReceptionAgree;
    }

    public void setEmailReceptionAgree(Boolean emailReceptionAgree) {
        this.emailReceptionAgree = emailReceptionAgree;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public String getChangePassword() {
        return changePassword;
    }

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