package ru.company.crud.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {
    private Long id;
    private String statusClass;
    private BigDecimal cost;
    private Flight flight;


    public Price(String statusClass, BigDecimal cost) {
        this.statusClass = statusClass;
        this.cost = cost;
    }

    public Price(Long id, String statusClass, BigDecimal cost) {
        this.id = id;
        this.statusClass = statusClass;
        this.cost = cost;
    }
}
