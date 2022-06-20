package ru.company.crud.faker;

import org.springframework.stereotype.Component;
import ru.company.crud.dto.FlightDTO;
import ru.company.crud.dto.PriceDTO;
import ru.company.crud.dto.TicketDTO;
import ru.company.crud.entity.Flight;
import ru.company.crud.entity.Price;
import ru.company.crud.entity.Ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class FlightFake {

    public Flight getFlightFake() {
        return Flight.builder()
                .departureCity("САМАРА")
                .departureDate(LocalDateTime.of(2022,4,1,15,0))
                .arrivalCity("МОСКВА")
                .arrivalDate(LocalDateTime.of(2022,4,1,16,0))
                .status("АКТИВНЫЙ")
                .build();
    }

   public Price getPriceFake() {
       return Price.builder()
               .statusClass("БИЗНЕС КЛАСС")
               .cost(new BigDecimal(10000))
               .build();
   }

   public Ticket getTicketFake() {
       return Ticket.builder()
               .surname("ИВАНОВ")
               .name("ИВАН")
               .patronymic("ИВАНОВИЧ")
               .seat("10")
               .build();
   }

    public FlightDTO getFlightDTOFake() {
        return FlightDTO.builder()
                .departureCity("САМАРА")
                .departDate(LocalDate.of(2022,4,1))
                .departTime(LocalTime.of(15,0))
                .arrivalCity("МОСКВА")
                .arDate(LocalDate.of(2022,4,1))
                .arTime(LocalTime.of(16,0))
                .status("АКТИВНЫЙ")
                .build();
    }

    public PriceDTO getPriceDTOFake() {
        return PriceDTO.builder()
                .id(1L)
                .statusClass("БИЗНЕС-КЛАСС")
                .cost(new BigDecimal(1500))
                .build();
    }

    public TicketDTO getTicketDTOFake() {
        return TicketDTO.builder()
                .id(1L)
                .surname("ИВАНОВ")
                .name("ИВАН")
                .patronymic("ИВАНОВИЧ")
                .seat("15")
                .build();
    }





}
