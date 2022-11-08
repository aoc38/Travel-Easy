package com.traveleasy.fullstackbackend.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.traveleasy.fullstackbackend.exception.UserNotFoundException;
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

    @Autowired
    private UserRepository userRepository;
    private CardRepository cardRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        User userData = userRepository.save(newUser);
        newUser.toString();
        if (newUser.getCards() != null) {
            Card userCardInfo = newUser.getCards().get(0);
            userCardInfo.setCardType(CardType.VISA);
            Card cardData = new Card(userCardInfo.getCardNumber(),userCardInfo.getExpiryDate(),userCardInfo.getCvv(),
                    userCardInfo.getCardType(),userCardInfo.getCardOwnerName(),true,userData);
            cardRepository.save(cardData);
            System.out.println(cardData);
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
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setFirstName(newUser.getFirstName());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with "+id+" has been deleted successfully";
    }

}
