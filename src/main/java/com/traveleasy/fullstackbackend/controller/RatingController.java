package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Card;
import com.traveleasy.fullstackbackend.model.Rating;
import com.traveleasy.fullstackbackend.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RatingController {

    public static final String RATING = "rating";
    @Autowired
    private RatingRepository ratingRepository;


    @GetMapping("/allratings")
    private List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @GetMapping("/rating/{id}")
    private Rating getRating(@PathVariable Long id) {
        return ratingRepository.findById(id).orElseThrow(() -> new NotFoundException(id, RATING));
    }

    @PostMapping("/addrating")
    private Rating addRating(Rating newRating) {
        return ratingRepository.save(newRating);
    }

    @PutMapping("/editrating/{id}")
    private Rating editRating(@PathVariable Long id, Rating newRating) {
        return ratingRepository.findById(id).map(userrating -> {
            userrating.setRatingNumber(newRating.getRatingNumber());
            userrating.setRatingComments(newRating.getRatingComments());
            return ratingRepository.save(userrating);
        }).orElseThrow(() -> new NotFoundException(id, RATING));
    }

    @DeleteMapping("/deleterating/{id}")
    private void deleteRating(@PathVariable Long id) {
        ratingRepository.deleteById(id);
    }
}
