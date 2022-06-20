package ru.company.crud.dto;

import ru.company.crud.entity.Flight;

public interface FlightDTOBuilder {
    FlightDTO fromFlight(Flight flight);
    Flight fromFlightDTO(FlightDTO flightDTO);
}
