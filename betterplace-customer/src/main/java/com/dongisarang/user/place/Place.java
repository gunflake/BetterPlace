package com.dongisarang.user.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue
    private Integer placeNo;

    private String placeName;

    private String intro; // 공간 소개

    private Integer partnerNo;

    private String tag;

    private String info; //시설 안내

    private String convenience; //편의시설

    private String notice; //예약시 주의사항

    private String address; // 주소

    private String website;

    private String email;

    private String phone; //대표 전화

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    private Date updateDate;

    private Byte state;

    private String image;

    //1-N 관계로 공간 상세정보 연결하기
    //private PlaceDtl placeDtl;

}
