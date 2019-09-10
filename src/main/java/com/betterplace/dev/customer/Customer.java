package com.betterplace.dev.customer;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String phone;

    @Column
    private  Integer age;

    @Builder
    public Customer(String name, String phone, Integer age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }
}
