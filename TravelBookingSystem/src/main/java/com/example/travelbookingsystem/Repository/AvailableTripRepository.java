package com.example.travelbookingsystem.Repository;


import com.example.travelbookingsystem.Entity.AvailableTrip;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/*
 * @Repository
 * - Marks it as Data Access Layer
 * - Handles DB operations automatically
 */
@Repository
public interface AvailableTripRepository extends JpaRepository<AvailableTrip, Long> {
}