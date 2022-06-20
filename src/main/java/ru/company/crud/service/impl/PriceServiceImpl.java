package ru.company.crud.service.impl;

import org.springframework.stereotype.Service;
import ru.company.crud.dto.PriceDTO;
import ru.company.crud.dto.PriceDTOBuilder;
import ru.company.crud.entity.Price;
import ru.company.crud.repository.PriceRepository;
import ru.company.crud.service.PriceService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    PriceRepository priceRepository;

    PriceDTOBuilder priceDTOBuilder;

    public PriceServiceImpl(PriceRepository priceRepository, PriceDTOBuilder priceDTOBuilder) {
        this.priceRepository = priceRepository;
        this.priceDTOBuilder = priceDTOBuilder;
    }

    @Override
    public PriceDTO save(PriceDTO priceDTO) {
        Price price = priceDTOBuilder.fromPriceDTO(priceDTO);
        return priceDTOBuilder.fromPrice(priceRepository.save(price));
    }

    @Override
    public PriceDTO update(PriceDTO priceDTO) {
        Price price = priceDTOBuilder.fromPriceDTO(priceDTO);
        Price updatePrice = priceRepository.update(price);
        return priceDTOBuilder.fromPrice(updatePrice);
    }

    @Override
    public PriceDTO findById(Long id) {
        PriceDTO priceDTObyId = priceDTOBuilder.fromPrice(priceRepository.findById(id).get());
        return priceDTObyId;
    }

    @Override
    public List<PriceDTO> findAll() {
        List<PriceDTO> priceAllDTO = priceRepository.findAll().stream()
                .map(price -> priceDTOBuilder.fromPrice(price))
                .collect(Collectors.toList());
        return priceAllDTO;
    }

    @Override
    public boolean delete(Long id) {
        return priceRepository.delete(id);
    }
}
