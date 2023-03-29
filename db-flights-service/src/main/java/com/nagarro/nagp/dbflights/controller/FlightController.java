package com.nagarro.nagp.dbflights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagp.dbflights.constants.FlightSeatStatus;
import com.nagarro.nagp.dbflights.dto.FlightSearchParameters;
import com.nagarro.nagp.dbflights.dto.OrderFlight;
import com.nagarro.nagp.dbflights.model.Flight;
import com.nagarro.nagp.dbflights.service.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping("")
	public List<Flight> searchFlights(
			@RequestParam(required = false, name = "source") String source,
			@RequestParam(required = false, name = "destination") String destination,
			@RequestParam(required = false, name = "departureDate") String departureDate
			) {
	
		
		List<Flight> flights = flightService.getAllFlights(new FlightSearchParameters(source, destination, departureDate));
		
		return flights;
	}
	
	@GetMapping("/{id}")
	public Flight getFlightById(@PathVariable("id") String flightId) {
//		System.out.println(flightId);
		Flight f = flightService.getFlightByFlightId(flightId);
		
		return f;
	}
	
	@PostMapping("/book")
	public OrderFlight bookSeats(@RequestBody OrderFlight booking) {
		OrderFlight bookingUpdate = flightService.updateSeatsStatus(booking);
		
		return bookingUpdate;
	}
}
