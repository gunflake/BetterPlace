package com.dongisarang.user.kakao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class KakaoReady {
    private String tid;
    private boolean tms_result;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private Date created_at;

    @Override
    public String toString() {
        return "KaKaoReady{" +
                "tid='" + tid + '\'' +
                "tms_result='" + tms_result + '\'' +
                ", next_redirect_app_url='" + next_redirect_app_url + '\'' +
                ", next_redirect_mobile_url='" + next_redirect_mobile_url + '\'' +
                ", next_redirect_pc_url='" + next_redirect_pc_url + '\'' +
                ", android_app_scheme='" + android_app_scheme + '\'' +
                ", ios_app_scheme='" + ios_app_scheme + '\'' +
                ", created_at=" + created_at.toString() +
                '}';
    }
}
