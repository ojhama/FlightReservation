package com.akash.flightreservation.services;

import com.akash.flightreservation.dto.ReservationRequest;
import com.akash.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}
