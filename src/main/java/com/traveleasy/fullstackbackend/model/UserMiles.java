package com.traveleasy.fullstackbackend.model;

import javax.persistence.*;

@Entity
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

    public int getMilesId() {
        return milesId;
    }

    public void setMilesId(int milesId) {
        this.milesId = milesId;
    }

    public int getMilesEarned() {
        return milesEarned;
    }

    public void setMilesEarned(int milesEarned) {
        this.milesEarned = milesEarned;
    }

    public int getMilesRedeemed() {
        return milesRedeemed;
    }

    public void setMilesRedeemed(int milesRedeemed) {
        this.milesRedeemed = milesRedeemed;
    }

    public int getMilesRemaining() {
        return milesRemaining;
    }

    public void setMilesRemaining(int milesRemaining) {
        this.milesRemaining = milesRemaining;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
