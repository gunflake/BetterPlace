package com.betterplace.dev.user;

import com.betterplace.dev.payment.Payment;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id @GeneratedValue
    private Integer userNo; //유저번호

    @Column(length = 20)
    private String userID; //유저아이디

    @Column(length = 20)
    private String nickName; //닉네임

    @Column(length = 20)
    private String password; //비밀번호

    @Column(length = 50)
    private String email; //이메일

    @Column
    private Byte state; //상태

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate; //등록날짜

    @Column
    private Date updDate; //수정날짜

    @Column
    private Date dropDate; //탈퇴날짜

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Payment> payments = new HashSet<>();

    public void addPayment(Payment payment){
        this.getPayments().add(payment);
        payment.setUser(this);

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public Date getDropDate() {
        return dropDate;
    }

    public void setDropDate(Date dropDate) {
        this.dropDate = dropDate;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public User(String userID, String nickName, String password, String email) {
        super();
        this.userID = userID;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }
}
