package com.dongisarang.admin.partner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
/*@ToString( exclude = )*/
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partnerNo; //파트너번호(자동 증가값)

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

}
