package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeDetailNo;

    @Column
    private String placeDetailName;

    @Column
    private String placeDetailIntro;

    @Column
    private Integer minCount;

    @Column
    private Integer maxCount;

}
