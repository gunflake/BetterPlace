package com.dongisarang.partner.place;

import com.dongisarang.partner.partner.Partner;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeNo; // 공간번호

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "partnerNo")
    private Partner partner;

    @OneToMany (mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceDtl> placeDtls;

    @OneToMany (mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceRefund> placeRefunds;

    @OneToMany (mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceInfo> placeInfos;


    public boolean addPlaceDtl (PlaceDtl placeDtl) {
        if(placeDtls == null){
            placeDtls = new ArrayList<>();
        }
        return this.placeDtls.add(placeDtl);
    }

    @Column(length = 50)
    private String placeName; // 공간명

    @Column
    @Lob
    private String intro; // 공간 소개

    @Column(length = 50)
    private String tag; // 태그

    @Column
    @Lob
    private String info; // 시설 안내

    @Column(length = 45)
    private String convenience; //편의시설

    @Column
    private String notice; // 예약 시 주의사항

    @Column(length = 100)
    private String address; // 주소

    @Column(length = 100)
    private String website; // 웹사이트

    @Column(length = 50)
    private String email; // 이메일

    @Column(length = 12)
    private String phone; // 대표전화번호

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate; //등록일

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate; //수정일

    @Column(length = 255)
    private String image; // 대표이미지

    @Column
    @ColumnDefault("1")
    private Byte state; //상태

    @Column
    private Integer defaultPrice;

    public void setPartner(Partner partner){

        if(this.partner != null){
            this.partner.getPlaces().remove(this);
        }

        this.partner = partner;
        partner.getPlaces().add(this);
    }

}
