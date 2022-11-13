package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Card;
import com.traveleasy.fullstackbackend.model.User;
import com.traveleasy.fullstackbackend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    public static final String CARD = "card";
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
    @GetMapping("/card/{id}")
    Card getCard(@PathVariable Long id, Card editCardData) {
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException(id, CARD));
    }
    @PutMapping("/card/{id}")
    Card editCard(@PathVariable Long id, Card editCardData){
        Card existingCard = cardRepository.findById(id).orElseThrow(()-> new NotFoundException(id,CARD));
        //save edited card info
        return cardRepository.save(existingCard);
    }
    @DeleteMapping("/card/{id}")
    private String deleteCard(@PathVariable Long id){
        if(!cardRepository.existsById(id)){
            throw new NotFoundException(id,CARD);
        }
        cardRepository.deleteById(id);
        return "Card with "+id+" has been deleted successfully";
    }
}
