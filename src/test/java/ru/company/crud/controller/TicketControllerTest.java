package ru.company.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.company.crud.dto.TicketDTO;
import ru.company.crud.dto.TicketDTOBuilder;
import ru.company.crud.faker.FlightFake;
import ru.company.crud.service.TicketService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TicketDTOBuilder ticketDTOBuilder;

    @MockBean
    TicketService ticketService;

    @InjectMocks
    FlightFake flightFake;

    private final static String URL = "/tickets";

    @Test
    void listTicket() throws Exception {
        List<TicketDTO> ticketDTOList = List.of(flightFake.getTicketDTOFake(),flightFake.getTicketDTOFake());
        when(ticketService.findAll()).thenReturn(ticketDTOList);
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ticketDTOList)));
        verify(ticketService, times(1)).findAll();
    }

    @Test
    void saveTicket() throws Exception {
        TicketDTO ticketDTO = flightFake.getTicketDTOFake();
        ticketDTO.setId(1L);
        when(ticketService.save(any(TicketDTO.class))).thenReturn(ticketDTO);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(ticketDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("ИВАН"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(ticketDTO)));
        verify(ticketService, times(1)).save(any(TicketDTO.class));
    }

    @Test
    void putTicket() throws Exception {
        TicketDTO ticketDTO = flightFake.getTicketDTOFake();
        ticketDTO.setId(1L);
        when(ticketService.update(any(TicketDTO.class))).thenReturn(ticketDTO);
        mockMvc.perform(put(URL + "/update-ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(ticketDTO)))
                .andExpect(jsonPath("$.name").value("ИВАН"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(ticketDTO)));
        verify(ticketService,times(1)).update(any(TicketDTO.class));
    }

    @Test
    void getFlight() throws Exception {
        TicketDTO ticketDTO = flightFake.getTicketDTOFake();
        ticketDTO.setId(1L);
        when(ticketService.findById(anyLong())).thenReturn(ticketDTO);
        mockMvc.perform(get(URL + "/ticket?id=1"))
                .andExpect(jsonPath("$.name").value("ИВАН"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json(objectMapper.writeValueAsString(ticketDTO)));
        verify(ticketService, times(1)).findById(anyLong());
    }

    @Test
    void deleteTrueTicket() throws Exception {
        when(ticketService.delete(anyLong())).thenReturn(true);
        mockMvc.perform(delete(URL + "/delete?id=1"))
                .andExpect(status().isOk());
        verify(ticketService,times(1)).delete(anyLong());
    }

    @Test
    void deleteFalseTicket() throws Exception {
        when(ticketService.delete(anyLong())).thenReturn(false);
        mockMvc.perform(delete(URL + "/delete?id=1"))
                .andExpect(status().isNotFound());
        verify(ticketService,times(1)).delete(anyLong());
    }
}
