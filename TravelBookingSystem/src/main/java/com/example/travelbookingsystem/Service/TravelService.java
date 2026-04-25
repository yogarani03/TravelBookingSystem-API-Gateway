package com.example.travelbookingsystem.Service;

import com.example.travelbookingsystem.Entity.AvailableTrip;
import com.example.travelbookingsystem.Entity.Booking;
import com.example.travelbookingsystem.Entity.Itinerary;
import com.example.travelbookingsystem.Entity.Refund;

import com.example.travelbookingsystem.Repository.AvailableTripRepository;
import com.example.travelbookingsystem.Repository.BookingRepository;
import com.example.travelbookingsystem.Repository.ItineraryRepository;
import com.example.travelbookingsystem.Repository.RefundRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Service
 * - Marks this class as Service Layer (Business Logic)
 */
@Service
public class TravelService {
    //REPOSITORY INJECTION
    @Autowired
    private AvailableTripRepository availableRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private ItineraryRepository itineraryRepo;

    @Autowired
    private RefundRepository refundRepo;
    // SEARCH TRIPS (source + destination)
    public List<AvailableTrip> searchTrips(String source, String destination) {

        List<AvailableTrip> trips = availableRepo.findAll();

        return trips.stream()
                .filter(t -> t.getSource().equalsIgnoreCase(source)
                        && t.getDestination().equalsIgnoreCase(destination))
                .toList();
    }
    //GET ALL AVAILABLE TRIPS
    public List<AvailableTrip> getAvailableTrips() {
        return availableRepo.findAll();
    }
    // BOOK TRIP
    public Booking bookTrip(Booking booking) {

        // set booking status
        booking.setStatus("CONFIRMED");

        // save booking
        Booking savedBooking = bookingRepo.save(booking);

        // create itinerary automatically
        Itinerary itinerary = new Itinerary();
        itinerary.setBookingId(savedBooking.getId());
        itinerary.setStatus("CONFIRMED");

        itineraryRepo.save(itinerary);

        return savedBooking;
    }
    //CANCEL BOOKING + REFUND
    public Booking cancelBooking(Long id) {

        // find booking
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // update booking status
        booking.setStatus("CANCELLED");
        bookingRepo.save(booking);

        // update itinerary status
        List<Itinerary> itineraries = itineraryRepo.findAll();

        for (Itinerary i : itineraries) {
            if (i.getBookingId().equals(id)) {
                i.setStatus("CANCELLED");
                itineraryRepo.save(i);
            }
        }

        // create refund
        Refund refund = new Refund();
        refund.setBookingId(id);
        refund.setAmount(booking.getAmount());
        refund.setStatus("PROCESSED");

        refundRepo.save(refund);

        return booking;
    }
    // GET ITINERARY
    public List<Itinerary> getItinerary() {
        return itineraryRepo.findAll();
    }
}