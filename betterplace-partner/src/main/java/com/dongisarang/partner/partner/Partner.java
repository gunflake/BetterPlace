package com.dongisarang.partner.partner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
@Getter
@Setter
/*@ToString( exclude = )*/
//@Table(uniqueConstraints = {@UniqueConstraint(name = "PARTNER_ID_UNIQUE", columnNames = {"partnerId"})})
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerNo; //파트너번호(자동 증가값)

    @Column(length = 50, unique = true, nullable = false)
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
