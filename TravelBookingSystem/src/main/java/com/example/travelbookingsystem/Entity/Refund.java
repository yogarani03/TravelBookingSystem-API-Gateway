package com.example.travelbookingsystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refunds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private double amount;

    private String status; // PROCESSED
}