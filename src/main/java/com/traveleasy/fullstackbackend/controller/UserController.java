package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.dto.LoginUserDto;
import com.traveleasy.fullstackbackend.dto.RegisterUserDto;
import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Card;
import com.traveleasy.fullstackbackend.model.CardType;
import com.traveleasy.fullstackbackend.model.User;
import com.traveleasy.fullstackbackend.repository.CardRepository;
import com.traveleasy.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
    public static final String USER = "user";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/usersignup")
    User addUser(@RequestBody RegisterUserDto newUser){

            //creating card object from dto
        System.out.println("card type string name : "+newUser.getCard().getCardType().name());
            if (newUser.getCard().getCardType().name() == "VISA"){
                newUser.getCard().setCardType(CardType.VISA);
            } else if (newUser.getCard().getCardType().name() == "MASTERCARD"){
                newUser.getCard().setCardType(CardType.MASTERCARD);
            }
            Card cardData = Card.builder()
                    .cardNumber(newUser.getCard().getCardNumber())
                    .cardType(newUser.getCard().getCardType())
                    .expiryDate(newUser.getCard().getExpiryDate())
                    .cvv(newUser.getCard().getCvv())
                    .cardOwnerName(newUser.getCard().getCardOwnerName())
                    .isDefault(true)
                    .build();

        //creating a list for card and adding card data from dto
        List<Card> cardsList = new ArrayList<>();
        cardsList.add(cardData);

        //creating userobject
        User userData = User.builder()
                .email(newUser.getEmail())
                .username(newUser.getUsername())
                .mailingAddress(newUser.getMailingAddress())
                .password(newUser.getPassword())
                .firstName(newUser.getFirstName())
                .middleName(newUser.getMiddleName())
                .lastName(newUser.getLastName())
                .cards(cardsList)
                .build();
        //save user data to DB
        User createdUserData = userRepository.save(userData);

        return createdUserData;
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

    @PostMapping("/userlogin")
    User loginCheck(@RequestBody LoginUserDto loginUserDto){
    //check user is present for that login
        User user = userRepository.findByUsernameAndPassword(loginUserDto.getUsername(),loginUserDto.getPassword());
        if (user == null){
            System.out.println("User is not found ");
        } else {
            System.out.println("User is found with that email and pwd");
        }
        return  user;
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
