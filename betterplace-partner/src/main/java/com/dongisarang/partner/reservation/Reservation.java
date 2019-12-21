package com.dongisarang.partner.reservation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Reservation {

    // TODO : 조인 추가 및 컬럼 수정 필요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer reservationNo;

    @Column
    public Integer placeNo;

    @Column
    public Integer placeDetailNo;

    @Column
    public Integer userCount;

    @Column
    public Integer customerNo;

    @Column
    public Integer paymentNo;

    //총결제금액
    @Column
    public Double totalPrice;

    //지불금액
    @Column
    public Double price;

    //쿠폰사용금액
    @Column
    public Double couponUsedPrice;

    @Column
    public String startDate;

    @Column
    public String endDate;

    @Column
    public String startTime;

    @Column
    public String endTime;

    @Column
    public Byte state;

    @CreationTimestamp
    private Date registerDate;

    @UpdateTimestamp
    private Date updateDate;

}
