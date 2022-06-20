package ru.company.crud.service;

import ru.company.crud.dto.PriceDTO;

import java.util.List;

public interface PriceService {
    PriceDTO save(PriceDTO price);
    PriceDTO update(PriceDTO price);
    PriceDTO findById(Long id);
    List<PriceDTO> findAll();
    boolean delete(Long id);
}
