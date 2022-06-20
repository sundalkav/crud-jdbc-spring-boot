package ru.company.crud.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.company.crud.dto.FlightDTO;
import ru.company.crud.dto.impl.FlightDTOBuilderImpl;
import ru.company.crud.entity.Flight;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.repository.FlightRepository;
import ru.company.crud.service.impl.FlightServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    FlightServiceImpl flightService;

    @Spy
    FlightDTOBuilderImpl flightDTOBuilder;

    @Spy
    FlightFake flightFake;


    @Test
    void save() {
        Flight flightFake = this.flightFake.getFlightFake();
        when(flightRepository.save(any())).thenReturn(flightFake);
        FlightDTO save = flightService.save(this.flightFake.getFlightDTOFake());
        assertNotNull(save);
        verify(flightRepository, times(1)).save(any());
    }

    @Test
    void update() {
        Flight flightFake = this.flightFake.getFlightFake();
        when(flightRepository.update(any())).thenReturn(flightFake);
        FlightDTO update = flightService.update(this.flightFake.getFlightDTOFake());
        assertNotNull(update);
        verify(flightRepository, times(1)).update(any());
    }

    @Test
    void findById() {
        Optional<Flight> flight = Optional.of(this.flightFake.getFlightFake());
        when(flightRepository.findById(anyLong())).thenReturn(flight);
        FlightDTO findById = flightService.findById(anyLong());
        assertNotNull(findById);
        verify(flightRepository, times(1)).findById(anyLong());
    }

    @Test
    void findAll() {
        List<Flight> flightList = List.of(this.flightFake.getFlightFake(), this.flightFake.getFlightFake());
        when(flightRepository.findAll()).thenReturn(flightList);
        List<FlightDTO> flightDTO = flightService.findAll();
        assertNotNull(flightDTO);
        verify(flightRepository, times(1)).findAll();
    }


    @Test
    void findAllPrices() {
        List<Flight> flightList = List.of(this.flightFake.getFlightFake(), this.flightFake.getFlightFake());
        when(flightRepository.findAllPrices()).thenReturn(flightList);
        List<FlightDTO> flightDTO = flightService.findAllPrices();
        assertNotNull(flightDTO);
        verify(flightRepository, times(1)).findAllPrices();
    }

    @Test
    void findAllTicketPrice() {
        List<Flight> flightList = List.of(this.flightFake.getFlightFake(), this.flightFake.getFlightFake());
        when(flightRepository.findAllTicketPrices()).thenReturn(flightList);
        List<FlightDTO> flightDTO = flightService.findAllTicketPrices();
        assertNotNull(flightDTO);
        verify(flightRepository, times(1)).findAllTicketPrices();
    }

    @Test
    void delete() {
        final boolean deleteFlight = flightService.delete(anyLong());
        verify(flightRepository,times(1)).delete(anyLong());
    }
}
