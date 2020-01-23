package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceDetailPrice {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="placedetailno")
    private PlaceDetail placeDetail;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeDtlPriceNo; // 공간번호

    @Column
    private Integer day; // 요일 1=월 2=화 3=수 ...

    @Column
    private Integer timeprice; // 시간당가격




}
