package ru.company.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.company.crud.dto.FlightDTO;
import ru.company.crud.dto.PriceDTO;
import ru.company.crud.dto.PriceDTOBuilder;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.service.PriceService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PriceDTOBuilder priceDTOBuilder;

    @MockBean
    PriceService priceService;

    @InjectMocks
    FlightFake flightFake;

    private final static String URL = "/prices";

    @Test
    void listPrice() throws Exception {
        List<PriceDTO> priceDTOList = List.of(flightFake.getPriceDTOFake(),flightFake.getPriceDTOFake());
        when(priceService.findAll()).thenReturn(priceDTOList);
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(priceDTOList)));
        verify(priceService, times(1)).findAll();
    }

    @Test
    void savePrice() throws Exception {
        PriceDTO priceDTO = flightFake.getPriceDTOFake();
        priceDTO.setId(1L);
        when(priceService.save(any(PriceDTO.class))).thenReturn(priceDTO);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(priceDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusClass").value("БИЗНЕС-КЛАСС"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(priceDTO)));
        verify(priceService, times(1)).save(any(PriceDTO.class));
    }

    @Test
    void putPrice() throws Exception {
        PriceDTO priceDTO = flightFake.getPriceDTOFake();
        priceDTO.setId(1L);
        when(priceService.update(any(PriceDTO.class))).thenReturn(priceDTO);
        mockMvc.perform(put(URL + "/update-price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(priceDTO)))
                .andExpect(jsonPath("$.statusClass").value("БИЗНЕС-КЛАСС"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(priceDTO)));
        verify(priceService,times(1)).update(any(PriceDTO.class));
    }

    @Test
    void getPrice() throws Exception {
        PriceDTO priceDTO = flightFake.getPriceDTOFake();
        priceDTO.setId(1L);
        when(priceService.findById(anyLong())).thenReturn(priceDTO);
        mockMvc.perform(get(URL + "/price?id=1"))
                .andExpect(jsonPath("$.statusClass").value("БИЗНЕС-КЛАСС"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(priceDTO)));
        verify(priceService, times(1)).findById(anyLong());
    }

    @Test
    void deleteTruePrice() throws Exception {
        when(priceService.delete(anyLong())).thenReturn(true);
        mockMvc.perform(delete(URL + "/delete?id=1"))
                .andExpect(status().isOk());
        verify(priceService,times(1)).delete(anyLong());
    }

    @Test
    void deleteFalsePrice() throws Exception {
        when(priceService.delete(anyLong())).thenReturn(false);
        mockMvc.perform(delete(URL + "/delete?id=1"))
                .andExpect(status().isNotFound());
        verify(priceService,times(1)).delete(anyLong());
    }
}
