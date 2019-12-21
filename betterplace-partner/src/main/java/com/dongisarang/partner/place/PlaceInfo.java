package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class PlaceInfo {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="placeNo")
    private Place place;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placdinfono; // 공간정보번호

    @Column
    private Integer day; // 요일

    @Column
    @CreationTimestamp
    private Date starttime; // 시작시간

    @Column
    @CreationTimestamp
    private Date endtime;  // 종료시간
}
