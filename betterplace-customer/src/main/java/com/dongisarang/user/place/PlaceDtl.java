package com.dongisarang.user.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceDtl {

    @Id
    @GeneratedValue
    private Integer placeDetailNo;

    //private Place place;

    private String placeDtlName;

    private String placeDtlIntro;

    private Short minCount;

    private Short maxCount;

}
