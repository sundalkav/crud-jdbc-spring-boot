package ru.company.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.company.crud.dto.FlightDTO;
import ru.company.crud.service.FlightService;

import java.util.List;


@RestController
@RequestMapping("/flights")
public class FlightController {

    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @GetMapping
    ResponseEntity<List<FlightDTO>> listFlight() {
        return new ResponseEntity<>(flightService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/flights-prices")
    ResponseEntity<List<FlightDTO>> listFlightPrices() {
        return new ResponseEntity<>(flightService.findAllPrices(), HttpStatus.OK);
    }

    @GetMapping("/flights-tickets")
    ResponseEntity<List<FlightDTO>> listFlightTickets() {
        return new ResponseEntity<>(flightService.findAllTicketPrices(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<FlightDTO> saveFlight(@RequestBody FlightDTO flightDTO) {
        return new ResponseEntity<>(flightService.save(flightDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update-flight")
    ResponseEntity<FlightDTO> putFlight(@RequestBody FlightDTO flightDTO) {
        return new ResponseEntity<>(flightService.update(flightDTO), HttpStatus.OK);
    }

    @GetMapping("/flight")
    ResponseEntity<FlightDTO> getFlight(@RequestParam("id") Long id) {
        FlightDTO flightDTO = flightService.findById(id);
        if(flightDTO.getId() == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flightDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deleteFlight(@RequestParam("id") Long id) {
        boolean delete = flightService.delete(id);
        if (!delete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
