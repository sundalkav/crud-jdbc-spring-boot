package ru.company.crud.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.company.crud.UnitTestBase;
import ru.company.crud.entity.Flight;
import ru.company.crud.entity.Price;
import ru.company.crud.entity.Ticket;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.repository.impl.FlightRepositoryImpl;
import ru.company.crud.repository.impl.PriceRepositoryImpl;
import ru.company.crud.repository.impl.TicketRepositoryImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositorylTest extends UnitTestBase {
    @Autowired
    DataSource testDataSource;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FlightFake flightFake;

    Ticket saveTicket;

    @BeforeEach
    void initSave() {
        Flight flightSave = flightRepository.save(flightFake.getFlightFake());
        Price price = flightFake.getPriceFake();
        price.setFlight(flightSave);
        Price savePrice = priceRepository.save(price);
        Ticket ticket = flightFake.getTicketFake();
        ticket.setFlight(flightSave);
        ticket.setPrice(savePrice);
        saveTicket = ticketRepository.save(ticket);
    }

    @Test
    void findById() {
        Optional<Ticket> byId = ticketRepository.findById(saveTicket.getId());
        assertEquals(saveTicket.getId(), byId.get().getId());
    }

    @Test
    void update() {
        saveTicket.setSeat("15");
        ticketRepository.update(saveTicket);
        Optional<Ticket> byId = ticketRepository.findById(saveTicket.getId());
        assertEquals("15", byId.get().getSeat());
    }

    @Test
    void findAll() {
        List<Ticket> allTicket = ticketRepository.findAll();
        assertFalse(allTicket.isEmpty());
    }

    @Test
    void delete() {
        assertTrue(ticketRepository.delete(saveTicket.getId()));
    }
}
