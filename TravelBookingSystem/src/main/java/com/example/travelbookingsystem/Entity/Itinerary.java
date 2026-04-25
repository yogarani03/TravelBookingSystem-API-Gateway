package com.example.travelbookingsystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itinerary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String status; // CONFIRMED / CANCELLED
}