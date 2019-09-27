package com.betterplace.dev.reserve;

import com.betterplace.dev.payment.Payment;
import com.betterplace.dev.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id @GeneratedValue
    private Integer reserveNo;

    @OneToOne
    @JoinColumn(name = "paymentNo")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private User user;

    private Double totalPrice;

    private Double realPrice;

    private Double cuponPrice;

    private Byte state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    private Date updDate;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Double getCuponPrice() {
        return cuponPrice;
    }

    public void setCuponPrice(Double cuponPrice) {
        this.cuponPrice = cuponPrice;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
}
