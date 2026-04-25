package com.example.travelbookingsystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "available_trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;

    private String type; // BUS / TRAIN / FLIGHT
    private boolean available;
    private double amount;
}