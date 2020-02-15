package com.dongisarang.user.place;

import com.dongisarang.user.customer.Customer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Comment {

    @Id @GeneratedValue
    private Integer commentNo;

    @ManyToOne
    @JoinColumn(name = "placeNo")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "customerNo")
    private Customer customer;

    @Lob
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Transient
    private String printDate;

    public void setPlace(Place place) {
        if(this.place != null){
            this.place.getComments().remove(this);
        }
        this.place = place;
        this.place.getComments().add(this);
    }

    public void setCustomer(Customer customer) {
        if(this.customer != null){
            this.customer.getComments().remove(this);
        }
        this.customer = customer;
        this.customer.getComments().add(this);
    }
}
