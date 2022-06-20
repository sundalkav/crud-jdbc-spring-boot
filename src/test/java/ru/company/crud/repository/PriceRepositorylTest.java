package ru.company.crud.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.company.crud.UnitTestBase;
import ru.company.crud.entity.Flight;
import ru.company.crud.entity.Price;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.repository.impl.FlightRepositoryImpl;
import ru.company.crud.repository.impl.PriceRepositoryImpl;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PriceRepositorylTest extends UnitTestBase {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FlightFake flightFake;

    Price savePrice;

    @BeforeEach
    void initSave() {
        Flight flightSave = flightRepository.save(flightFake.getFlightFake());
        Price price = flightFake.getPriceFake();
        price.setFlight(flightSave);
        savePrice = priceRepository.save(price);
    }

    @Test
    void findById() {
        Optional<Price> byId = priceRepository.findById(savePrice.getId());
        assertEquals(savePrice.getId(), byId.get().getId());
    }

    @Test
    void update() {
        savePrice.setCost(new BigDecimal(10000));
        priceRepository.update(savePrice);
        Optional<Price> byId = priceRepository.findById(savePrice.getId());
        assertEquals(new BigDecimal(10000), byId.get().getCost());
    }

    @Test
    void findAll() {
        List<Price> priceList = priceRepository.findAll();
        assertFalse(priceList.isEmpty());
    }

    @Test
    void delete() {
        assertTrue(priceRepository.delete(savePrice.getId()));
    }
}
