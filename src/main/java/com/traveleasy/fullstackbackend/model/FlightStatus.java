package com.traveleasy.fullstackbackend.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlightStatus {
    private String flightNumber;
    private String airlineName;
    private Date departureDate;
    private FlightStatusType status;




}
