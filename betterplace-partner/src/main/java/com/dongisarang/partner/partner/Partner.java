package com.dongisarang.partner.partner;

import com.dongisarang.partner.place.Place;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
/*@ToString( exclude = )*/
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partnerNo; //파트너번호(자동 증가값)

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Place> places;

    @Column
    private String partnerId; //아이디 (이메일형식)

    @Column
    private String partnerPassword; //비밀번호

    @Column
    private String nickname; //닉네임

    @CreationTimestamp
    private Date registerDate; //등록일

    @UpdateTimestamp
    private Date updateDate; //수정일

    @Column
    private Byte state; //상태

    @Transient
    private String changePassword; // 변경 비밀번호

}
