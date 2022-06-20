package ru.company.crud.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.company.crud.UnitTestBase;
import ru.company.crud.entity.Flight;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.repository.impl.FlightRepositoryImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlightRepositoryTest extends UnitTestBase {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightFake flightFake;

    Flight flightSave;

    @BeforeEach
    void initSave() {
        flightSave = flightRepository.save(flightFake.getFlightFake());
    }


    @Test
    void findById() {
        Optional<Flight> getFlight = flightRepository.findById(flightSave.getId());
        assertEquals(flightSave.getId(), getFlight.get().getId());
    }

    @Test
    void update() {
        flightSave.setDepartureCity("Самара");
        flightRepository.update(flightSave);
        Optional<Flight> getFlight = flightRepository.findById(flightSave.getId());
        assertEquals("Самара", getFlight.get().getDepartureCity());
    }

    @Test
    void delete() {
        boolean deleteFlightId = flightRepository.delete(flightSave.getId());
        assertTrue(deleteFlightId);
    }

    @Test
    void findAll() {
        List<Flight> flightList = flightRepository.findAll();
        assertFalse(flightList.isEmpty());
    }

}
