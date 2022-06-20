package ru.company.crud.dto;

import ru.company.crud.entity.Ticket;

public interface TicketDTOBuilder {
    TicketDTO fromPrice(Ticket price);
    Ticket fromPriceDTO(TicketDTO priceDTO);
}
