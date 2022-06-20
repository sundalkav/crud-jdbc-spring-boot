package ru.company.crud.service;

import ru.company.crud.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    TicketDTO save(TicketDTO ticket);
    TicketDTO findById(Long id);
    TicketDTO update(TicketDTO ticket);
    List<TicketDTO> findAll();
    boolean delete(Long id);
}
