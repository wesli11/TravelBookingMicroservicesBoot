package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import booking.dto.BookingDTO;
import booking.dto.BookingRequestFlightDTO;
import external.dto.FlightRequestDTO;
import external.dto.FlightResponseDTO;
import external.dto.HotelAvailableRequestDTO;
import external.dto.HotelAvailableResponseDTO;
import service.BookingService;

@RestController
public class BookingController {

	@Autowired
	BookingService service;
	//Flight
	@GetMapping(value="flightsavailable",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FlightResponseDTO>> listAvailableFlight(@RequestBody FlightRequestDTO dto){
		return new ResponseEntity<List<FlightResponseDTO>> (service.listFlightResponse(dto),HttpStatus.OK);
	}

	@PostMapping(value="booking-flight",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingDTO> createBookingFlight(@RequestBody BookingRequestFlightDTO bookingRequestDTO){
		BookingDTO dtoResponse= service.createBookingForFlight(bookingRequestDTO);
		return dtoResponse!=null?new ResponseEntity<>(dtoResponse,HttpStatus.OK):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//Hotel
	@GetMapping(value="booking-hotel",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HotelAvailableResponseDTO>> listAvailableHotel(@RequestBody HotelAvailableRequestDTO dto){
		System.out.println(dto.getFrom()+"\n");
		System.out.println(dto.getTo());

		return new ResponseEntity<List<HotelAvailableResponseDTO>> (service.listHotelResponse(dto),HttpStatus.OK);
	}
}
