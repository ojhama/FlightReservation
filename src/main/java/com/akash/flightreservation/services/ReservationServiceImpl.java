package com.akash.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akash.flightreservation.dto.ReservationRequest;
import com.akash.flightreservation.entities.Flight;
import com.akash.flightreservation.entities.Passenger;
import com.akash.flightreservation.entities.Reservation;
import com.akash.flightreservation.repos.FlightRepository;
import com.akash.flightreservation.repos.PassengerRepository;
import com.akash.flightreservation.repos.ReservationRepository;
import com.akash.flightreservation.util.EmailUtil;
import com.akash.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.akash.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	private static final Logger LOGGER= LoggerFactory.getLogger(ReservationServiceImpl.class);
			
	@Autowired
	private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Transactional
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		// Make Payment
		LOGGER.info("Inside bookFlight()");
		Long flightId=request.getFlightId();
		LOGGER.info("Fetching flight for flight id:"+flightId);
		Flight flight=flightRepository.findById(flightId).get();
		
		Passenger passenger=new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Saving the passenger:"+passenger);
		Passenger savedPassenger=passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		Reservation savedReservation = reservationRepository.save(reservation);
		String filePath = ITINERARY_DIR+savedReservation.getId()+".pdf";
		LOGGER.info("Generating the itinerary");
		pdfGenerator.generateItierary(savedReservation, filePath);
		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		return savedReservation;
	}

}
