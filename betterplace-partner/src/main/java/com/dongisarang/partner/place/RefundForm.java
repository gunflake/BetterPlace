package com.dongisarang.partner.place;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefundForm {
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;
    private String day6;
    private String day7;

    public int[] getDaysIntArr(){
        int [] results = new int[7];
        results[0] = Integer.parseInt(day1.substring(0, day1.length()-1));
        results[1] = Integer.parseInt(day2.substring(0, day2.length()-1));
        results[2] = Integer.parseInt(day3.substring(0, day3.length()-1));
        results[3] = Integer.parseInt(day4.substring(0, day4.length()-1));
        results[4] = Integer.parseInt(day5.substring(0, day5.length()-1));
        results[5] = Integer.parseInt(day6.substring(0, day6.length()-1));
        results[6] = Integer.parseInt(day7.substring(0, day7.length()-1));
        return results;
    }
}
