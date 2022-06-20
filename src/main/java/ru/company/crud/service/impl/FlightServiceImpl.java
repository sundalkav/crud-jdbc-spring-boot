package ru.company.crud.service.impl;

import org.springframework.stereotype.Service;
import ru.company.crud.dto.FlightDTO;
import ru.company.crud.dto.FlightDTOBuilder;
import ru.company.crud.entity.Flight;
import ru.company.crud.repository.FlightRepository;
import ru.company.crud.service.FlightService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    FlightRepository flightRepository;

    FlightDTOBuilder flightDTOBuilder;

    public FlightServiceImpl(FlightRepository flightRepository, FlightDTOBuilder flightDTOBuilder) {
        this.flightRepository = flightRepository;
        this.flightDTOBuilder = flightDTOBuilder;
    }

    @Override
    public FlightDTO save(FlightDTO flightDTO) {
        Flight flight = flightDTOBuilder.fromFlightDTO(flightDTO);
        return flightDTOBuilder.fromFlight(flightRepository.save(flight));
    }

    @Override
    public FlightDTO update(FlightDTO flightDTO) {
        Flight flight = flightDTOBuilder.fromFlightDTO(flightDTO);
        FlightDTO flightUpdateDTO = flightDTOBuilder.fromFlight(flightRepository.update(flight));
        return flightUpdateDTO;
    }

    @Override
    public FlightDTO findById(Long id) {
        FlightDTO flightDTO = flightDTOBuilder.fromFlight(flightRepository.findById(id).get());
        return flightDTO;
    }

    @Override
    public List<FlightDTO> findAll() {
        List<FlightDTO> flightDTOS = flightRepository.findAll()
                .stream()
                .map(flight -> flightDTOBuilder.fromFlight(flight))
                .collect(Collectors.toList());
        return flightDTOS;
    }

    @Override
    public List<FlightDTO> findAllPrices() {
        List<FlightDTO> flightDTOS = flightRepository.findAllPrices()
                .stream()
                .map(flight -> flightDTOBuilder.fromFlight(flight))
                .collect(Collectors.toList());
        return flightDTOS;
    }

    @Override
    public List<FlightDTO> findAllTicketPrices() {
        List<FlightDTO> flightDTOS = flightRepository.findAllTicketPrices()
                .stream()
                .map(flight -> flightDTOBuilder.fromFlight(flight))
                .collect(Collectors.toList());
        return flightDTOS;
    }

    @Override
    public boolean delete(Long id) {
        return flightRepository.delete(id);
    }
}
