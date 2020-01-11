package com.dongisarang.user.reservation;

import com.dongisarang.user.customer.Customer;
import com.dongisarang.user.place.Place;
import com.dongisarang.user.place.PlaceDetail;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Reservation {
    @Id
    @GeneratedValue
    private Integer reservationNo;

    @ManyToOne
    @JoinColumn(name = "placeNo")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "customerNo")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "placeDetailNo")
    private PlaceDetail placeDetail;

    @Column
    private Byte customerCount;

    @Column
    private Integer price;

    //yyyyMMdd 형식으로 저장(예: 20190820)
    @Column(length = 8)
    private String reservationDate;

    @Column
    private Byte startTime;

    @Column
    private Byte endTime;

    @Column
    @ColumnDefault("1")
    private Byte state;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;

    public void setPlace(Place place) {
        if(this.place != null){
            this.place.getReservations().remove(this);
        }
        this.place = place;
        place.getReservations().add(this);
    }

    public void setCustomer(Customer customer) {
        if(this.customer != null){
            this.customer.getReservations().remove(this);
        }
        this.customer = customer;
        customer.getReservations().add(this);
    }

    public void setPlaceDetail(PlaceDetail placeDetail) {
        if(this.placeDetail != null){
            this.placeDetail.getReservations().remove(this);
        }
        this.placeDetail = placeDetail;
        placeDetail.getReservations().add(this);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "place=" + place +
                ", customer=" + customer +
                ", placeDetail=" + placeDetail +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
