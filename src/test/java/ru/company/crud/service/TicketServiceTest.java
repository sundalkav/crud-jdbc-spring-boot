package ru.company.crud.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.company.crud.dto.TicketDTO;
import ru.company.crud.dto.impl.TicketDTOBuilderImpl;
import ru.company.crud.entity.Ticket;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.repository.TicketRepository;
import ru.company.crud.service.impl.TicketServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketServiceImpl ticketService;

    @Spy
    TicketDTOBuilderImpl ticketDTOBuilder;

    @Spy
    FlightFake flightFake;

    @Test
    void save() {
        Ticket ticketFake = this.flightFake.getTicketFake();
        when(ticketRepository.save(any())).thenReturn(ticketFake);
        TicketDTO ticketDTO = ticketService.save(this.flightFake.getTicketDTOFake());
        assertNotNull(ticketDTO);
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void update() {
        Ticket ticketFake = this.flightFake.getTicketFake();
        when(ticketRepository.update(any())).thenReturn(ticketFake);
        TicketDTO updateTicket = ticketService.update(this.flightFake.getTicketDTOFake());
        verify(ticketRepository, times(1)).update(any());
    }

    @Test
    void findById() {
        Optional<Ticket> ticket = Optional.of(this.flightFake.getTicketFake());
        when(ticketRepository.findById(anyLong())).thenReturn(ticket);
        TicketDTO findById = ticketService.findById(anyLong());
        assertNotNull(findById);
        verify(ticketRepository, times(1)).findById(anyLong());
    }

    @Test
    void findAll() {
        List<Ticket> ticketList = List.of(this.flightFake.getTicketFake(), this.flightFake.getTicketFake());
        when(ticketRepository.findAll()).thenReturn(ticketList);
        List<TicketDTO> ticketDTO = ticketService.findAll();
        assertNotNull(ticketDTO);
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        final boolean deleteTicket = ticketService.delete(anyLong());
        verify(ticketRepository, times(1)).delete(anyLong());
    }
}
