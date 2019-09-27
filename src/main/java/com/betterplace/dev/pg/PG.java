package com.betterplace.dev.pg;

import com.betterplace.dev.payment.Payment;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PG {

    @Id @GeneratedValue
    private int pgNo;

    @Column(length = 50)
    private String pgName;

    @OneToMany(mappedBy = "pg", cascade = CascadeType.ALL)
    private Set<Payment> payments = new HashSet<>();

    public void addPayment(Payment payment){
        this.getPayments().add(payment);
        payment.setPg(this);
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
