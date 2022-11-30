package com.akash.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
