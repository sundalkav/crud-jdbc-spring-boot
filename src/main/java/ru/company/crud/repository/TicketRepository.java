package ru.company.crud.repository;

import ru.company.crud.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    Ticket update(Ticket ticket);
    List<Ticket> findAll();
    List<Ticket> findAllTicket(Long id);
    List<Ticket> findAllTicketPrice(Long id);
    boolean delete(Long id);

}
