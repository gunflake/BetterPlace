package com.dongisarang.partner.partner;

import com.dongisarang.partner.place.Place;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Partner {

    @Column
    private String partnerPassword; //비밀번호
    @Id @GeneratedValue
    private Integer partnerNo;

    @Column(length = 45)
    private String partnerId;
	@Column
    private String nickname; //닉네임

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

    @Transient
    private String changePassword; // 변경 비밀번호

}
