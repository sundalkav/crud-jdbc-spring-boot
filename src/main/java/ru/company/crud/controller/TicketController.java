package ru.company.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.company.crud.dto.TicketDTO;
import ru.company.crud.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    ResponseEntity<List<TicketDTO>> listTicket() {
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<TicketDTO> saveTicket(@RequestBody TicketDTO ticketDTO) {
        return new ResponseEntity<>(ticketService.save(ticketDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update-ticket")
    ResponseEntity<TicketDTO> putTicket(@RequestBody TicketDTO ticketDTO) {
        return new ResponseEntity<>(ticketService.update(ticketDTO), HttpStatus.OK);
    }

    @GetMapping("/ticket")
    ResponseEntity<TicketDTO> getFlight(@RequestParam("id") Long id) {
        TicketDTO ticketDTO = ticketService.findById(id);
        if(ticketDTO.getId() == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deleteTicket(@RequestParam("id") Long id) {
        boolean delete = ticketService.delete(id);
        if (!delete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
