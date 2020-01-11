package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class PlaceDetail {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="placeNo")
    private Place place;

    @OneToMany (mappedBy = "placeDtl", cascade = CascadeType.ALL)
    private List<PlaceDetailPrice> placeDetailPrices;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placedetailno; // 세부공간번호

    @Column
    private String placedtlname; // 세부공간명 1호, 2호, 회의실 등

    @Column
    private String placedtlintro; // 세부공간소개

    @Column
    private Integer mincount; // 최소예약인원

    @Column
    private Integer maxcount; // 최대예약인원

    // 기본 생성자
    public PlaceDetail() {}

    public PlaceDetail(Place place, String placedtlname, String placedtlintro, Integer mincount, Integer maxcount)
    {
        this.place          = place;
        this.placedtlname   = placedtlname;
        this.placedtlintro  = placedtlintro;
        this.mincount       = mincount;
        this.maxcount       = maxcount;
    }


}
