package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceDtl {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="placeNo")
    private Place place;

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

    public PlaceDtl(String placedtlname, String placedtlintro, Integer mincount, Integer maxcount)
    {
        this.placedtlname   = placedtlname;
        this.placedtlintro  = placedtlintro;
        this.mincount       = mincount;
        this.maxcount       = maxcount;
    }

    public PlaceDtl(Place place, String placedtlname, String placedtlintro, Integer mincount, Integer maxcount)
    {
        this.place          = place;
        this.placedtlname   = placedtlname;
        this.placedtlintro  = placedtlintro;
        this.mincount       = mincount;
        this.maxcount       = maxcount;
    }
}
