package ru.company.crud.dto.impl;

import org.springframework.stereotype.Component;
import ru.company.crud.dto.PriceDTO;
import ru.company.crud.dto.PriceDTOBuilder;
import ru.company.crud.entity.Price;

@Component
public class PriceDTOBuilderImpl implements PriceDTOBuilder {

    @Override
    public PriceDTO fromPrice(Price price) {
        return PriceDTO.builder()
                .id(price.getId())
                .statusClass(price.getStatusClass())
                .cost(price.getCost())
                .flight(price.getFlight())
                .build();
    }

    @Override
    public Price fromPriceDTO(PriceDTO priceDTO) {
        return Price.builder()
                .id(priceDTO.getId())
                .statusClass(priceDTO.getStatusClass())
                .cost(priceDTO.getCost())
                .flight(priceDTO.getFlight())
                .build();
    }
}
