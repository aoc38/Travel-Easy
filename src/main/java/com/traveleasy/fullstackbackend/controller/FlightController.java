package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Flight;
import com.traveleasy.fullstackbackend.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {
    public static final String FLIGHT = "flight";
    @Autowired
    private FlightRepository flightRepository;
    @PostMapping("/flight")
    Flight addFlight(@RequestBody Flight newFlight){
        return  flightRepository.save(newFlight);
    }

    //To edit flight information need to edit this according what to change in flight data and when to change
    @PutMapping("/flight/{id}")
    Flight updateFlight(@RequestBody Flight newFlight, @PathVariable Long id){
        return flightRepository.findById(id)
                .map(flight -> {
                    flight.setAirlineName(newFlight.getAirlineName());
                    flight.setMiles(newFlight.getMiles());
                    flight.setSource(newFlight.getSource());
                    flight.setDestination(newFlight.getDestination());
                    flight.setDepartureDate(newFlight.getDepartureDate());
                    flight.setReturnDate(newFlight.getReturnDate());
                    flight.setPrice(newFlight.getPrice());
                    return flightRepository.save(flight);
                }).orElseThrow(()->new NotFoundException(id,FLIGHT));
    }
    @GetMapping("/flights")
    List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }
    @GetMapping("/flight/{id}")
    Flight getFlightById(@PathVariable Long id){
        return flightRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id,FLIGHT));
    }

    @DeleteMapping("/flight/{id}")
    String deleteFlightById(@PathVariable long id){
        if(!flightRepository.existsById(id)){
            throw new NotFoundException(id,FLIGHT);
        }
        flightRepository.deleteById(id);
        return "Flight with "+id+" has been deleted successfully";
    }


}
