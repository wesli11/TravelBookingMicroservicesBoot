package com.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceptions.CodeNotExist;
import com.exceptions.InvalidDateRangeException;
import com.exceptions.InvalidParameterException;
import com.exceptions.UnAvailableCapacity;
import com.model.FlightEntity;
import com.repository.FlightEntityJpaRepository;

import dto.FlightDTO;
import dto.FlightMapper;

@Service
public class ServiceFlightImpl implements ServiceFlight {
	@Autowired
	FlightEntityJpaRepository repository;
	
	@Autowired
    FlightMapper mapper;
	@Override
	public List<FlightDTO> listFlightAvailableBeetwen(String origin, String destination, LocalDate startDate,
			LocalDate endDate) {
		if(origin.isEmpty() || destination.isEmpty() || origin==null || destination==null) {
			throw new InvalidParameterException("origin and destination cannot empty or null") ;
		}
		if(startDate.isAfter(endDate)) {
			throw new InvalidDateRangeException("Dates incorrects");
		}
		LocalDateTime startDateTime = startDate.atStartOfDay();  // 00:00:00 del startDate
	    LocalDateTime endDateTime = endDate.atTime(23, 59, 59);  // 23:59:59 del endDate

		return mapper.toListDTO(repository.listFlightAvailable(origin, destination, startDateTime, endDateTime));
	}

//	@Override
//	public FlightEntity findByFlight(int idFlight) {
//		return repository.findById(idFlight).orElseThrow(()->new CodeNotExist("Code not exist "+idFlight));
//	}
	public FlightDTO findByFlight(int idFlight) {
		FlightEntity entity= repository.findById(idFlight).orElseThrow(()->new CodeNotExist("Code not exist "+idFlight));
	     return mapper.toDTO(entity);
	}
	@Override
	public void updateSeatFlight(int idFlight, int seat) {
		   FlightEntity flight=repository.findById(idFlight).orElseThrow(()->new CodeNotExist("Code not exist "+idFlight));
		   if(flight.getAvailableSeats()<seat) {
			   throw new UnAvailableCapacity("Sorry seat not available");
		   }
		   flight.setAvailableSeats(flight.getAvailableSeats()-seat);
		   repository.saveAndFlush(flight);
	}

	@Override
	public List<FlightEntity> listFlightDirect(String origin, String destination, LocalDateTime startDate,
			LocalDateTime endDate) {
		if(origin.isEmpty() || destination.isEmpty() || origin==null || destination==null) {
			throw new InvalidParameterException("origin and destination cannot empty or null") ;
		}
		if(startDate.isAfter(endDate)) {
			throw new InvalidDateRangeException("Dates incorrects");
		}
		return repository.listFlightAvailableDirect(origin, destination, startDate, endDate);
	}
	 /**
     * Bloquea asientos para un vuelo específico durante un tiempo limitado.
     *
     * @param idFlight   ID del vuelo
     * @param seats      Número de asientos a bloquear
     * @param durationMinutes Duración del bloqueo en minutos
     * @return true si el bloqueo fue exitoso, false en caso contrario
     */

	@Override
	public boolean blockSeats(int idFlight, int seats) {
	   LocalDateTime expiration = LocalDateTime.now().plusMinutes(10);
	     int rowsUpdated = repository.blockSeats(idFlight, seats, expiration);
	     return rowsUpdated>0;
	
	}

	

}
