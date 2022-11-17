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
public class Flight {

    @Id
    @GeneratedValue
    private Long id;
    private String airlineName;
    private String source;
    private String destination;
    private Date departureDate;
    private Date returnDate;
    private TripType tripType;//ONEWAY OR ROUND TRIP
    private FlightType flightType;//DOMESTIC OR INTERNATIONAL
    private int price;
    private int miles;
    private String flightNumber;

}
