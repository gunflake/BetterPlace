package com.dongisarang.partner.reservation;

import com.dongisarang.partner.place.Place;
import com.dongisarang.partner.place.PlaceDetail;
import com.dongisarang.user.customer.Customer;
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

    @ManyToOne
    @JoinColumn(name = "placeNo")
    public Place place;

    @ManyToOne
    @JoinColumn(name="customerNo")
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "placeDetailNo")
    public PlaceDetail placeDetail;

    @Column
    public Byte customerCount;

    @Column
    public Double price;

    @Column(length = 8)
    public String reservationDate;

    @Column
    public String startTime;

    @Column
    public String endTime;

    @Column
    public Byte state;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;

}
