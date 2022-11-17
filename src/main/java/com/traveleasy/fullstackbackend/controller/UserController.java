package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Card;
import com.traveleasy.fullstackbackend.model.CardType;
import com.traveleasy.fullstackbackend.model.User;
import com.traveleasy.fullstackbackend.repository.CardRepository;
import com.traveleasy.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
    public static final String USER = "user";
    @Autowired
    private UserRepository userRepository;
    private CardRepository cardRepository;

    @PostMapping("/user")
    User addUser(@RequestBody User newUser){
        User userData = userRepository.save(newUser);
       System.out.println(newUser.toString());
        if (newUser.getCards() != null) {
            Card userCardInfo = newUser.getCards().get(0);
            userCardInfo.setCardType(CardType.VISA);
            Card cardData = Card.builder().cardNumber(userCardInfo.getCardNumber())
                    .expiryDate(userCardInfo.getExpiryDate())
                    .cvv(userCardInfo.getCvv())
                    .cardType(userCardInfo.getCardType())
                    .cardOwnerName(userCardInfo.getCardOwnerName())
                    .isDefault(true)
                    .user(userData)
                    .build();
            cardRepository.save(cardData);
            System.out.println(cardData.toString());
        }
        return userData;
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id,USER));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setFirstName(newUser.getFirstName());
                    return userRepository.save(user);
                }).orElseThrow(()->new NotFoundException(id,USER));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable long id){
        if(!userRepository.existsById(id)){
            throw new NotFoundException(id,USER);
        }
        userRepository.deleteById(id);
        return "User with "+id+" has been deleted successfully";
    }

}
