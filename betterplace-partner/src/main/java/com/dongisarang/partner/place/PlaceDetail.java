package com.dongisarang.partner.place;

import com.dongisarang.partner.reservation.Reservation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class PlaceDetail {

    @Id
    @GeneratedValue
    private Integer placeDetailNo;

    @ManyToOne
    @JoinColumn(name = "placeNo")
    private Place place;

    @Column(length = 255)
    private String placeDetailName;

    @Column(length = 50)
    private String placeDetailIntro;

    private Short minCount;

    private Short maxCount;

    @OneToMany(mappedBy = "placeDetail", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    public void setPlace(Place place) {

        if (this.place != null) {
            this.place.getPlaceDetails().remove(this);
        }

        this.place = place;
        place.getPlaceDetails().add(this);
    }

    public PlaceDetail(){

    }

    public PlaceDetail(Place place, String placedtlname, String placedtlintro, Short mincount, Short maxcount) {
        this.place = place;
        this.placeDetailName = placedtlname;
        this.placeDetailIntro = placedtlintro;
        this.minCount = mincount;
        this.maxCount = maxcount;
        setPlace(place);
    }
}