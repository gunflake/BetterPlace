package com.dongisarang.partner.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Board {
    @Id
    @GeneratedValue
    private Integer boardNo; // 게시글 번호

    private String title; // 게시글 제목

    private String content;

    private Integer boardType; // 0:공지사항, 도움말 (1:회원, 2:예약 및 결제, 3:취소 및 환불, 4:공간이용 및 후기, 5:기타)
}