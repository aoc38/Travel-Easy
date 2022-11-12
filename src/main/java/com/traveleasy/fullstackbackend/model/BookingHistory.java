package com.traveleasy.fullstackbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingHistory {
    @GeneratedValue
    @Id
    private int bookingId;
    private int paymentId;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flightInfo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userInfo;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelInfo;

}
