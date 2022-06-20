package ru.company.crud.dto.impl;

import org.springframework.stereotype.Component;
import ru.company.crud.dto.FlightDTO;
import ru.company.crud.dto.FlightDTOBuilder;
import ru.company.crud.entity.Flight;

import java.time.LocalDateTime;

@Component
public class FlightDTOBuilderImpl implements FlightDTOBuilder {

    @Override
    public FlightDTO fromFlight(Flight flight) {
        return FlightDTO.builder()
                .id(flight.getId())
                .departureCity(flight.getDepartureCity())
                .departureDate(flight.getDepartureDate())
                .arrivalCity(flight.getArrivalCity())
                .arrivalDate(flight.getArrivalDate())
                .status(flight.getStatus())
                .prices(flight.getPrices())
                .tickets(flight.getTickets())
                .build();
    }

    @Override
    public Flight fromFlightDTO(FlightDTO flightDTO) {
        return Flight.builder()
                .id(flightDTO.getId())
                .departureCity(flightDTO.getDepartureCity())
                .departureDate(LocalDateTime.of(flightDTO.getDepartDate(), flightDTO.getDepartTime()))
                .arrivalCity(flightDTO.getArrivalCity())
                .arrivalDate(LocalDateTime.of(flightDTO.getArDate(), flightDTO.getArTime()))
                .status(flightDTO.getStatus())
                .build();
    }
}
