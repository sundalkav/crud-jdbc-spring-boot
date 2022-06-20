package ru.company.crud.dto.impl;

import org.springframework.stereotype.Component;
import ru.company.crud.dto.TicketDTO;
import ru.company.crud.dto.TicketDTOBuilder;
import ru.company.crud.entity.Ticket;

@Component
public class TicketDTOBuilderImpl implements TicketDTOBuilder {
    @Override
    public TicketDTO fromPrice(Ticket price) {
        return TicketDTO.builder()
                .id(price.getId())
                .surname(price.getSurname())
                .name(price.getName())
                .patronymic(price.getPatronymic())
                .seat(price.getSeat())
                .flight(price.getFlight())
                .price(price.getPrice())
                .build();
    }

    @Override
    public Ticket fromPriceDTO(TicketDTO priceDTO) {
        return Ticket.builder()
                .id(priceDTO.getId())
                .surname(priceDTO.getSurname())
                .name(priceDTO.getName())
                .patronymic(priceDTO.getPatronymic())
                .seat(priceDTO.getSeat())
                .flight(priceDTO.getFlight())
                .price(priceDTO.getPrice())
                .build();
    }
}
