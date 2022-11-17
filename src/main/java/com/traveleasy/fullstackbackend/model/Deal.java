package com.traveleasy.fullstackbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Deal {
    @Id
    @GeneratedValue
    private Long id;
    private String dealName;
    private String source;
    private String destination;
    private Timestamp travelData;
    private Timestamp returnDate;
    private int lowPrice;
    private int highPrice;
    private int dealPrice;
    private int dealMiles;

}
