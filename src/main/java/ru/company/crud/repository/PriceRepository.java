package ru.company.crud.repository;

import ru.company.crud.entity.Price;
import java.util.List;
import java.util.Optional;

public interface PriceRepository {

    Price save(Price price);
    Price update(Price price);
    Optional<Price> findById(Long id);
    List<Price> findAll();
    List<Price> findAllFlights(Long id);
    boolean delete(Long id);

}
