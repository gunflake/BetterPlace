package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceRefund {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="placeNo")
    private Place place;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 번호
    private Integer placerefundno;

    @Column
    // 이용전날짜
    private Integer beforeday;

    @Column
    // 환불%금액
    private Integer refundpercent;
}
