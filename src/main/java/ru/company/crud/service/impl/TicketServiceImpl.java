package ru.company.crud.service.impl;

import org.springframework.stereotype.Service;
import ru.company.crud.dto.TicketDTO;
import ru.company.crud.dto.TicketDTOBuilder;
import ru.company.crud.entity.Ticket;
import ru.company.crud.repository.TicketRepository;
import ru.company.crud.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;

    TicketDTOBuilder ticketDTOBuilder;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketDTOBuilder ticketDTOBuilder) {
        this.ticketRepository = ticketRepository;
        this.ticketDTOBuilder = ticketDTOBuilder;
    }

    @Override
    public TicketDTO save(TicketDTO ticketDTO) {
        Ticket ticket = ticketDTOBuilder.fromPriceDTO(ticketDTO);
        return ticketDTOBuilder.fromPrice(ticketRepository.save(ticket));
    }

    @Override
    public TicketDTO findById(Long id) {
        return ticketDTOBuilder.fromPrice(ticketRepository.findById(id).get());
    }

    @Override
    public TicketDTO update(TicketDTO ticketDTO) {
        Ticket ticket = ticketDTOBuilder.fromPriceDTO(ticketDTO);
        Ticket updatePrice = ticketRepository.update(ticket);
        return ticketDTOBuilder.fromPrice(updatePrice);
    }

    @Override
    public List<TicketDTO> findAll() {
        List<TicketDTO> ticketAllDTO = ticketRepository.findAll().stream()
                .map(ticket -> ticketDTOBuilder.fromPrice(ticket))
                .collect(Collectors.toList());
        return ticketAllDTO;
    }

    @Override
    public boolean delete(Long id) {
        return ticketRepository.delete(id);
    }
}
