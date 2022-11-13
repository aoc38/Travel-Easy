package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Deal;
import com.traveleasy.fullstackbackend.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DealController {
    public static final String DEAL = "Deal";
    @Autowired
    private DealRepository dealRepository;

    @PostMapping("/deal")
    private Deal addDeal(Deal newDeal){
        return dealRepository.save(newDeal);
    }

    @PutMapping("/deal/{id}")
    private Deal editDeal(Deal editDeal,@PathVariable Long id){
        Deal existingDeal = dealRepository.findById(id).orElseThrow(() -> new NotFoundException(id,DEAL));
        //add edited data here
       return dealRepository.save(existingDeal);
    }

    @GetMapping("/deals")
    private List<Deal> getAllDeals(){
        return dealRepository.findAll();
    }
    @GetMapping("/deal/{id}")
    private Deal getDeal(@PathVariable Long id){
        return dealRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(id,DEAL));
    }

    @DeleteMapping("/deal/{id}")
    private String deleteDeal(@PathVariable Long id){
        if(!dealRepository.existsById(id)){
            throw new NotFoundException(id,DEAL);
        }
        dealRepository.deleteById(id);
        return "Deal with "+id+" has been deleted successfully";
    }
}
