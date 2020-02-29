package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceRefund {

    // 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeRefundNo;

    @ManyToOne
    @JoinColumn(name="placeNo")
    private Place place;


    // 이용전날짜
    @Column
    private Integer beforeDay;

    // 환불%금액
    @Column
    private Integer refundPercent;

    public void setPlace(Place place) {
        if(this.place != null){
            this.place.getPlaceRefunds().remove(this);
        }
        this.place = place;
        this.place.getPlaceRefunds().add(this);
    }
}
