package ru.company.crud.dto;


import lombok.*;
import ru.company.crud.entity.Flight;
import ru.company.crud.entity.Price;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketDTO {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String seat;
    private Flight flight;
    private Price price;
}
