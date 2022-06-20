package ru.company.crud.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.company.crud.dto.PriceDTO;
import ru.company.crud.dto.impl.PriceDTOBuilderImpl;
import ru.company.crud.entity.Price;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.repository.PriceRepository;
import ru.company.crud.service.impl.PriceServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    PriceServiceImpl priceService;

    @Spy
    PriceDTOBuilderImpl priceDTOBuilder;

    @Spy
    FlightFake flightFake;

    @Test
    void save() {
        Price priceFake = this.flightFake.getPriceFake();
        when(priceRepository.save(any())).thenReturn(priceFake);
        PriceDTO priceDTO = priceService.save(this.flightFake.getPriceDTOFake());
        assertNotNull(priceDTO);
        verify(priceRepository, times(1)).save(any());
    }

    @Test
    void update() {
        Price priceFake = this.flightFake.getPriceFake();
        when(priceRepository.update(any())).thenReturn(priceFake);
        PriceDTO updatePrice = priceService.update(this.flightFake.getPriceDTOFake());
        verify(priceRepository, times(1)).update(any());
    }

    @Test
    void findById() {
        Optional<Price> price = Optional.of(this.flightFake.getPriceFake());
        when(priceRepository.findById(anyLong())).thenReturn(price);
        PriceDTO findById = priceService.findById(anyLong());
        assertNotNull(findById);
        verify(priceRepository, times(1)).findById(anyLong());
    }

    @Test
    void findAll() {
        List<Price> priceList = List.of(this.flightFake.getPriceFake(), this.flightFake.getPriceFake());
        when(priceRepository.findAll()).thenReturn(priceList);
        List<PriceDTO> priceDTO = priceService.findAll();
        assertNotNull(priceDTO);
        verify(priceRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        final boolean deletePrice = priceService.delete(anyLong());
        verify(priceRepository, times(1)).delete(anyLong());
    }
}
