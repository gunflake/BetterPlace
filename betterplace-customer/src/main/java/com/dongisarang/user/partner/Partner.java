package com.dongisarang.user.partner;

import com.dongisarang.user.place.Place;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Partner {

    @Id @GeneratedValue
    private Integer partnerNo;

    @Column(length = 45)
    private String partnerId;

    @Column
    private String partnerPassword;

    @Column(length = 50)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column
    private Byte state;

    //양방향 연관관계 매핑
    @OneToMany(mappedBy = "partner")
    private List<Place> places = new ArrayList<>();
}
