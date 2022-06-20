package ru.company.crud.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String seat;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Flight flight;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Price price;

    public Ticket(String surname, String name, String patronymic, String seat) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.seat = seat;
    }

    public Ticket(Long id, String surname, String name, String patronymic, String seat, Price price) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.seat = seat;
        this.price = price;
    }
}
