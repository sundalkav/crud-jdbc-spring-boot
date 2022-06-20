package ru.company.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.company.crud.dto.PriceDTO;
import ru.company.crud.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {

    PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    ResponseEntity<PriceDTO> listPrice() {
        return new ResponseEntity(priceService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PriceDTO> savePrice(@RequestBody PriceDTO priceDTO) {
        return new ResponseEntity<>(priceService.save(priceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update-price")
    ResponseEntity<PriceDTO> putPrice(@RequestBody PriceDTO priceDTO) {
        return new ResponseEntity<>(priceService.update(priceDTO), HttpStatus.OK);
    }

    @GetMapping("/price")
    ResponseEntity<PriceDTO> getFlight(@RequestParam("id") Long id) {
        PriceDTO priceDTO = priceService.findById(id);
        if(priceDTO.getId() == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(priceDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deletePrice(@RequestParam("id") Long id) {
        boolean delete = priceService.delete(id);
        if (!delete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
