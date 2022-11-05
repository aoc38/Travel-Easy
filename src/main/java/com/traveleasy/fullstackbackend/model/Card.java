package com.traveleasy.fullstackbackend.model;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long cardId;
    private Long cardNumber;
    private String expiryDate;
    private int cvv;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private String cardOwnerName;

    public String getCardOwnerName() {
        return cardOwnerName;
    }
    private boolean isDefault;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Card() {
        super();
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
