package com.traveleasy.fullstackbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserMiles {
    @Id
    @GeneratedValue
    private int milesId;
    private int milesEarned;
    private int milesRedeemed;
    private int milesRemaining;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
