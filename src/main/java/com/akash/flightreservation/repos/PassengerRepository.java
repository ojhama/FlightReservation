package com.akash.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
