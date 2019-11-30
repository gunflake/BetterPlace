package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;
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

    @OneToMany (mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceDtl> placeDtls;

    public boolean addPlaceDtl (PlaceDtl placeDtl) {
        if(placeDtls == null){
            placeDtls = new ArrayList<>();
        }
        return this.placeDtls.add(placeDtl);
    }

    @Column
    private String placeName; // 공간명

    @Column
    private String intro; // 공간 소개

    @Column
    private String tag; // 태그

    @Column
    private String info; // 시설 안내

    @Column
    private String convenience; // 편의시설

    @Column
    private String notice; // 예약 시 주의사항

    @Column
    private String address; // 주소

    @Column
    private String website; // 웹사이트

    @Column
    private String email; // 이메일

    @Column
    private String phone; // 대표전화번호

    @CreationTimestamp
    private Date registerDate; //등록일

    @UpdateTimestamp
    private Date updateDate; //수정일

    @Column
    private String image; // 대표이미지

    @Column
    private Byte state; //상태

}
