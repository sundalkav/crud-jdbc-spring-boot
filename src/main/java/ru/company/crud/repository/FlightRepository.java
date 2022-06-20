package ru.company.crud.repository;

import ru.company.crud.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {
    Flight save(Flight flight);
    Flight update(Flight flight);
    Optional<Flight> findById(Long id);
    List<Flight> findAll();
    List<Flight> findAllPrices();
    List<Flight> findAllTicketPrices();
    boolean delete(Long id);


}
