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
import ru.company.crud.dto.FlightDTOBuilder;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.service.FlightService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    FlightDTOBuilder flightDTOBuilder;

    @MockBean
    FlightService flightService;

    @InjectMocks
    FlightFake flightFake;

    private final static String URL = "/flights";

    @Test
    void listFlight() throws Exception {
        List<FlightDTO> flightDTOList = List.of(flightFake.getFlightDTOFake(),flightFake.getFlightDTOFake());
        when(flightService.findAll()).thenReturn(flightDTOList);
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(flightDTOList)));
        verify(flightService, times(1)).findAll();
    }

    @Test
    void listFlightPrices() throws Exception {
        List<FlightDTO> flightDTOList = List.of(flightFake.getFlightDTOFake(),flightFake.getFlightDTOFake());
        when(flightService.findAllPrices()).thenReturn(flightDTOList);
        mockMvc.perform(get(URL + "/flights-prices"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(flightDTOList)));
        verify(flightService, times(1)).findAllPrices();
    }

    @Test
    void listFlightTickets() throws Exception {
        List<FlightDTO> flightDTOList = List.of(flightFake.getFlightDTOFake(),flightFake.getFlightDTOFake());
        when(flightService.findAllTicketPrices()).thenReturn(flightDTOList);
        mockMvc.perform(get(URL + "/flights-tickets"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(flightDTOList)));
        verify(flightService, times(1)).findAllTicketPrices();
    }

    @Test
    void saveFlight() throws Exception {
        FlightDTO flightDTO = flightFake.getFlightDTOFake();
        flightDTO.setId(1L);
        when(flightService.save(any(FlightDTO.class))).thenReturn(flightDTO);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(flightDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.departureCity").value("САМАРА"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(flightDTO)));
        verify(flightService, times(1)).save(any(FlightDTO.class));
    }

    @Test
    void putFlight() throws Exception {
        FlightDTO flightDTO = flightFake.getFlightDTOFake();
        flightDTO.setId(1L);
        when(flightService.update(any(FlightDTO.class))).thenReturn(flightDTO);
        mockMvc.perform(put(URL + "/update-flight")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(flightDTO)))
                .andExpect(jsonPath("$.departureCity").value("САМАРА"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(flightDTO)));
        verify(flightService,times(1)).update(any(FlightDTO.class));
    }

    @Test
    void getFlight() throws Exception {
        FlightDTO flightDTO = flightFake.getFlightDTOFake();
        flightDTO.setId(1L);
        when(flightService.findById(anyLong())).thenReturn(flightDTO);
        mockMvc.perform(get(URL + "/flight?id=1"))
                .andExpect(jsonPath("$.departureCity").value("САМАРА"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(flightDTO)));
        verify(flightService, times(1)).findById(anyLong());
    }

    @Test
    void deleteTrueFlight() throws Exception {
        when(flightService.delete(anyLong())).thenReturn(true);
        mockMvc.perform(delete(URL + "/delete?id=1"))
                .andExpect(status().isOk());
        verify(flightService,times(1)).delete(anyLong());
    }

    @Test
    void deleteFalseFlight() throws Exception {
        when(flightService.delete(anyLong())).thenReturn(false);
        mockMvc.perform(delete(URL + "/delete?id=1"))
                .andExpect(status().isNotFound());
        verify(flightService,times(1)).delete(anyLong());
    }

}
