package com.traveleasy.fullstackbackend.controller;

import com.traveleasy.fullstackbackend.dto.FlightDto;
import com.traveleasy.fullstackbackend.exception.NotFoundException;
import com.traveleasy.fullstackbackend.model.Flight;
import com.traveleasy.fullstackbackend.model.Passenger;
import com.traveleasy.fullstackbackend.model.User;
import com.traveleasy.fullstackbackend.repository.FlightRepository;
import com.traveleasy.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.traveleasy.fullstackbackend.controller.UserController.USER;

@RestController
@CrossOrigin("http://localhost:3000/")
public class FlightController {
    public static final String FLIGHT = "flight";
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/bookflight/{id}")
    Flight addFlight(@RequestBody FlightDto newBooking, @PathVariable Long id){

        User userinfo = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("",id,USER));
        //save flight and passenger info in db
        System.out.println("USER _INFO " +userinfo);
        System.out.println("DATA FROM API " +newBooking.getPassengerData().getList());
        List<Passenger> passengerList = new ArrayList<>();
        passengerList.addAll(newBooking.getPassengerData().getList());
        System.out.println("Passenger Data in save Flight booking : "+passengerList.toString());
        Flight data = newBooking.getFlightData();
        Flight newFlightBooking = Flight.builder()
                .flightNumber(data.getFlightNumber())
                .airline(data.getAirline())
                .miles(data.getMiles())
                .arrivalCityName(data.getArrivalCityName())
                .departureCityName(data.getDepartureCityName())
                .departureDate(data.getDepartureDate())
                .returnDate(data.getReturnDate())
                .price(data.getPrice())
                .passengersList(passengerList)
                .user(userinfo)
                .build();
        Flight savedFlightData =   flightRepository.save(newFlightBooking);
        System.out.println("saved flight data : "+savedFlightData.toString());
        return savedFlightData;
    }

    //To edit flight information need to edit this according what to change in flight data and when to change
//    @PutMapping("/flight/{id}")
//    Flight updateFlight(@RequestBody Flight newFlight, @PathVariable Long id){
//        return flightRepository.findById(id)
//                .map(flight -> {
//                    flight.setAirlineName(newFlight.getAirlineName());
////                    flight.setMiles(newFlight.getMiles());
//                    flight.setSource(newFlight.getSource());
//                    flight.setDestination(newFlight.getDestination());
//                    flight.setDepartureDate(newFlight.getDepartureDate());
//                    flight.setReturnDate(newFlight.getReturnDate());
////                    flight.setPrice(newFlight.getPrice());
//                    return flightRepository.save(flight);
//                }).orElseThrow(()->new NotFoundException(id,FLIGHT));
//    }
    @GetMapping("/flights")
    List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }
    @GetMapping("/flight/{id}")
    Flight getFlightById(@PathVariable Long id){
        return flightRepository.findById(id)
                .orElseThrow(()->new NotFoundException("",id,FLIGHT));
    }

    @DeleteMapping("/flight/{id}")
    String deleteFlightById(@PathVariable long id){
        if(!flightRepository.existsById(id)){
            throw new NotFoundException("",id,FLIGHT);
        }
        flightRepository.deleteById(id);
        return "Flight with "+id+" has been deleted successfully";
    }


}
