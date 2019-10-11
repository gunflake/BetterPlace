package com.betterplace.dev.Partner;

import com.betterplace.dev.payment.Payment;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Partner {

    @Id @GeneratedValue
    private Integer partnerNo; //유저번호

    @Column(length = 20, unique = true)
    private String partnerID; //아이디

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


    public Integer getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(Integer partnerNo) {
        this.partnerNo = partnerNo;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
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

    public Partner() {
    }

    public Partner(String partnerID, String password, String email) {
        super();
        this.partnerID = partnerID;
        this.password = password;
        this.email = email;
    }
}
