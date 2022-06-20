package ru.company.crud.service;

import ru.company.crud.dto.FlightDTO;
import java.util.List;

public interface FlightService {

    FlightDTO save(FlightDTO flightDTO);
    FlightDTO update(FlightDTO flightDTO);
    FlightDTO findById(Long id);
    List<FlightDTO> findAll();
    List<FlightDTO> findAllPrices();
    List<FlightDTO> findAllTicketPrices();
    boolean delete(Long id);
}
