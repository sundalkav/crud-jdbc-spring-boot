package ru.company.crud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ru.company.crud.entity.Price;
import ru.company.crud.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDTO {
    private Long id;
    private String departureCity;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime departureDate;
    private LocalDate departDate;
    private LocalTime departTime;
    private String arrivalCity;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime arrivalDate;
    private LocalDate arDate;
    private LocalTime arTime;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Price> prices;
    private List<Ticket> tickets;

}
