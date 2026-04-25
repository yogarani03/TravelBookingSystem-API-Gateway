package com.example.travelbookingsystem.Controller;

import com.example.travelbookingsystem.Entity.AvailableTrip;
import com.example.travelbookingsystem.Entity.Booking;
import com.example.travelbookingsystem.Entity.Itinerary;
import com.example.travelbookingsystem.Service.TravelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 REST Controller for Travel Booking System
 */
@RestController
@RequestMapping("/api")
public class TravelController {

    // Injecting Service Layer
    @Autowired
    private TravelService service;

    //SEARCH TRIPS (source + destination)
    // Example:
    // GET http://localhost:8080/api/search?source=Chennai&destination=Bangalore
    @GetMapping("/search")
    public List<AvailableTrip> searchTrips(
            @RequestParam String source,
            @RequestParam String destination) {

        return service.searchTrips(source, destination);
    }
    //GET ALL AVAILABLE TRIPS
    // Example:
    // GET http://localhost:8080/api/available
    @GetMapping("/available")
    public List<AvailableTrip> getAvailableTrips() {
        return service.getAvailableTrips();
    }

    // BOOK TRIP
    // Example:
    // POST http://localhost:8080/api/book
    // Body:
    // {
    //   "source": "Chennai",
    //   "destination": "Bangalore",
    //   "amount": 500
    // }
    @PostMapping("/book")
    public Booking bookTrip(@RequestBody Booking booking) {
        return service.bookTrip(booking);
    }
    // CANCEL BOOKING
    // Example:
    // PUT http://localhost:8080/api/cancel/1
    @PutMapping("/cancel/{id}")
    public Booking cancelBooking(@PathVariable Long id) {
        return service.cancelBooking(id);
    }
    // GET ITINERARY
    // Example:
    // GET http://localhost:8080/api/itinerary
    @GetMapping("/itinerary")
    public List<Itinerary> getItinerary() {
        return service.getItinerary();
    }
}