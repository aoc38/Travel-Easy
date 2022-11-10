package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Hotel;
import com.traveleasy.fullstackbackend.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class HotelController {
    public static final String HOTEL = "hotel";
    @Autowired
    private HotelRepository HotelRepository;
    @PostMapping("/Hotel")
    Hotel addHotel(@RequestBody Hotel newHotel){
        return  HotelRepository.save(newHotel);
    }

    //To edit Hotel information need to edit this according what to change in Hotel data and when to change
    @PutMapping("/Hotel/{id}")
    Hotel updateHotel(@RequestBody Hotel newHotel, @PathVariable Long id){
        return HotelRepository.findById(id)
                .map(hotel -> {
                   hotel.setDestination(newHotel.getDestination());
                   hotel.setCheckInDate(newHotel.getCheckInDate());
                   hotel.setCheckOutDate(newHotel.getCheckOutDate());
                   hotel.setRoomPrice(newHotel.getRoomPrice());
                   hotel.setRoomsCount(newHotel.getRoomsCount());
                   hotel.setGuestsCount(newHotel.getGuestsCount());
                    return HotelRepository.save(hotel);
                }).orElseThrow(()->new NotFoundException(id,HOTEL));
    }
    @GetMapping("/Hotels")
    List<Hotel> getAllHotels(){
        return HotelRepository.findAll();
    }
    @GetMapping("/Hotel/{id}")
    Hotel getHotelById(@PathVariable Long id){
        return HotelRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id,HOTEL));
    }

    @DeleteMapping("/Hotel/{id}")
    String deleteHotelById(@PathVariable long id){
        if(!HotelRepository.existsById(id)){
            throw new NotFoundException(id,HOTEL);
        }
        HotelRepository.deleteById(id);
        return "Hotel with "+id+" has been deleted successfully";
    }

}
