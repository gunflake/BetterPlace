package com.betterplace.dev.payment;

import com.betterplace.dev.pg.PG;
import com.betterplace.dev.reserve.Reservation;
import com.betterplace.dev.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    @Id @GeneratedValue
    private Integer paymentNo; // 결제번호

    @ManyToOne
    @JoinColumn(name = "userNo")
    private User user; //유저정보 << 유저번호

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Reservation reservation; //예약

    @ManyToOne
    @JoinColumn(name = "pgNo")
    private PG pg; //PG 정보

    @Column
    private Double price; //금액

    private Byte state; //상태

    @Column(length = 50)
    private String tid;

    @Column(length = 50)
    private String cid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate; //등록날짜

    @Column
    private Date updDate; //수정날짜

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PG getPg() {
        return pg;
    }

    public void setPg(PG pg) {
        this.pg = pg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    @Override
    public String toString() {
        return "Payment{" +
                "paymentNo=" + paymentNo +
                ", reservation=" + reservation +
                ", price=" + price +
                ", state=" + state +
                ", tid='" + tid + '\'' +
                ", cid='" + cid + '\'' +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                ", userName=" + user.getNickName() +
                '}';
    }
}
