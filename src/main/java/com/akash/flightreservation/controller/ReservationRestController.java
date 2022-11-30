package com.akash.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akash.flightreservation.dto.ReservationUpdateRequest;
import com.akash.flightreservation.entities.Reservation;
import com.akash.flightreservation.repos.ReservationRepository;
import com.akash.flightreservation.services.ReservationServiceImpl;

@RestController
public class ReservationRestController {

	private static final Logger LOGGER= LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reservationRepository;
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id")Long id)
	{
		LOGGER.info("Inside findReservation() for id: "+id);
		return reservationRepository.findById(id).get();
	}
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request)
	{
		LOGGER.info("Inside updateReservation() for "+request);
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		LOGGER.info("Saving Reservation");
		return reservationRepository.save(reservation);
	}
}
