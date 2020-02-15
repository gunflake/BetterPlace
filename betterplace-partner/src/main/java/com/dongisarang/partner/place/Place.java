package com.dongisarang.partner.place;

import com.dongisarang.partner.partner.Partner;
import com.dongisarang.partner.reservation.Reservation;
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
    @GeneratedValue
    private Integer placeNo;

    @ManyToOne
    @JoinColumn(name = "partnerNo")
    private Partner partner;

    @Column(length = 50)
    private String placeName;

    @Column
    @Lob
    private String intro; // 공간 소개

    @Column(length = 50)
    private String tag;

    @Column
    @Lob
    private String info; //시설 안내

    @Column(length = 45)
    private String convenience; //편의시설

    @Column
    @Lob
    private String notice; //예약시 주의사항

    @Column(length = 100)
    private String address; // 주소

    @Column(length = 6)
    private String zipcode; // 우편주소

    @Column(length = 100)
    private String addressdetail; // 주소(상세)

    @Column(length = 100)
    private String website;

    @Column(length = 50)
    private String email;

    @Column(length = 12)
    private String phone; //대표 전화

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;

    @Column
    @ColumnDefault("1")
    private Byte state;

    @Column(length = 255)
    private String image;

    @Column
    private Integer defaultPrice;

    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<PlaceDetail> placeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @Transient
    private ArrayList<String> tags;

    @Transient
    private ArrayList<String> infos;

    @Transient
    private ArrayList<String> notices;

    public Place() {
        this.state = 1;
    }

    public void setPartner(Partner partner){

        if(this.partner != null){
            this.partner.getPlaces().remove(this);
        }

        this.partner = partner;
        partner.getPlaces().add(this);
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeNo=" + placeNo +
                ", placeName='" + placeName + '\'' +
                '}';
    }
}
