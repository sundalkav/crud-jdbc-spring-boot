package ru.company.crud.dto;

import ru.company.crud.entity.Price;

public interface PriceDTOBuilder {
    PriceDTO fromPrice(Price price);
    Price fromPriceDTO(PriceDTO priceDTO);
}
