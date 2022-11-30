package com.akash.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.akash.flightreservation.dto.ReservationRequest;
import com.akash.flightreservation.entities.Flight;
import com.akash.flightreservation.entities.Reservation;
import com.akash.flightreservation.repos.FlightRepository;
import com.akash.flightreservation.services.ReservationService;
@Controller
public class ReservationController {
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ReservationService reservationService;
	
	private Logger LOGGER=LoggerFactory.getLogger(ReservationController.class);
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId,ModelMap modelMap)
	{
		LOGGER.info("Inside showCompleteReservation() invoked with the flight id:"+flightId);
		Flight flight=flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		LOGGER.info("Flight is:"+flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation",method = RequestMethod.POST)
	public String completeReservation(ReservationRequest reservationRequest,ModelMap modelMap)
	{
		Reservation reservation = reservationService.bookFlight(reservationRequest);
		modelMap.addAttribute("msg", "Reservation created successfully and the id is  "+reservation.getId());
		return "reservationConfirmation";
	}
}
