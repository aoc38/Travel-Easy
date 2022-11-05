package com.traveleasy.fullstackbackend.model;

import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private int ratingId;
    private int ratingNumber;
    private String ratingComments;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public String getRatingComments() {
        return ratingComments;
    }

    public void setRatingComments(String ratingComments) {
        this.ratingComments = ratingComments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
