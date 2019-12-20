package com.dongisarang.user.place;

import com.dongisarang.user.partner.Partner;
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

    @ManyToOne
    @JoinColumn(name = "partnerNo")
    private Partner partner;

    @Column(length = 50)
    private String placeName;

    @Column(length = 100)
    private String intro; // 공간 소개

    @Column(length = 50)
    private String tag;

    @Column
    private String info; //시설 안내

    @Column(length = 45)
    private String convenience; //편의시설

    @Column
    private String notice; //예약시 주의사항

    @Column(length = 100)
    private String address; // 주소

    @Column(length = 100)
    private String website;

    @Column(length = 50)
    private String email;

    @Column(length = 10)
    private String phone; //대표 전화

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column
    private Byte state;

    @Column(length = 100)
    private String image;

    public void setPartner(Partner partner){

        if(this.partner != null){
            this.partner.getPlaces().remove(this);
        }

        this.partner = partner;
        partner.getPlaces().add(this);
    }

}
