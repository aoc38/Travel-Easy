package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.model.Card;
import com.traveleasy.fullstackbackend.model.User;
import com.traveleasy.fullstackbackend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/card")
    Card addCard(@RequestBody Card newCard){
        return  cardRepository.save(newCard);
    }
    @GetMapping("/cards")
    List<Card> getAllCards(){
        return cardRepository.findAll();
    }
}
