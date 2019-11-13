package com.dongisarang.admin.partner;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class Partner {
    @Id @GeneratedValue
    private Integer partnerNo;

    private String partnerId;

    private String partnerPassword;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate; //등록날짜

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate; //등록날짜

    private Byte state;

}
