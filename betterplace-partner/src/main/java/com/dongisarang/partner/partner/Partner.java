package com.dongisarang.partner.partner;

import com.dongisarang.partner.place.Place;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
/*@ToString( exclude = )*/
@ToString
public class Partner {

    @Id
    @GeneratedValue
    private Integer partnerNo; //파트너번호(자동 증가값)

    @Column
    @Size(min = 4)
    private String partnerId; //아이디 (이메일형식)

    @Column
    @Size(min = 4)
    private String partnerPassword; //비밀번호

    @Column
    private String nickname; //닉네임

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate; //등록일

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate; //수정일

    @Column
    private Byte state; //상태

    @Transient
    private String changePassword; // 변경 비밀번호

    //양방향 연관관계 매핑
    @OneToMany(mappedBy = "partner")
    private List<Place> places = new ArrayList<>();

}
