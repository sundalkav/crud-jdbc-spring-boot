package ru.company.crud.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Flight {
    private Long id;
    private String departureCity;
    private LocalDateTime departureDate;
    private String arrivalCity;
    private LocalDateTime arrivalDate;
    private String status;
    private List<Price> prices;
    private List<Ticket> tickets;

    public Flight(Long id, String departureCity, LocalDateTime departureDate, String arrivalCity, LocalDateTime arrivalDate, String status) {
        this.id = id;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.status = status;
    }

    public Flight(String departureCity, LocalDateTime departureDate, String arrivalCity, LocalDateTime arrivalDate, String status) {
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.status = status;
    }


    public Flight(String departureCity, LocalDateTime departureDate, String arrivalCity, LocalDateTime arrivalDate, String status, List<Price> prices) {
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.status = status;
        this.prices = prices;
    }


}
