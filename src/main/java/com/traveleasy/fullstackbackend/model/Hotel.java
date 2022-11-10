package com.traveleasy.fullstackbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;
    private String destination;
    private Date checkInDate;
    private Date checkOutDate;
    private int roomsCount;
    private int guestsCount;
    private int roomPrice;


}
