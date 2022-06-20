package ru.company.crud.dto;

import lombok.*;
import ru.company.crud.entity.Flight;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PriceDTO {
    private Long id;
    private String statusClass;
    private BigDecimal cost;
    private Flight flight;
}

