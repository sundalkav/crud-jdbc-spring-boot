package ru.company.crud.repository.impl;

import org.springframework.stereotype.Repository;
import ru.company.crud.entity.Flight;
import ru.company.crud.exception.DaoException;
import ru.company.crud.repository.FlightRepository;
import ru.company.crud.repository.PriceRepository;
import ru.company.crud.repository.TicketRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

    DataSource dataSource;

    PriceRepository priceRepository;

    TicketRepository ticketRepository;

    public FlightRepositoryImpl(DataSource dataSource, PriceRepository priceRepository, TicketRepository ticketRepository) {
        this.dataSource = dataSource;
        this.priceRepository = priceRepository;
        this.ticketRepository = ticketRepository;
    }


    private static final String SAVE_SQL = "INSERT INTO " +
            "flight (departure_city,departure_date,arrival_city,arrival_date,status) " +
            "VALUES (?,?,?,?,?);";

    private static final String DELETE_SQL = "DELETE FROM flight CASCADE WHERE id = ?;";

    private static final String UPDATE_SQL = "UPDATE flight SET departure_city = ?, " +
            "departure_date = ?, arrival_city = ?, arrival_date = ?, status = ? WHERE id = ?;";

    private static final String FIND_BY_ID_SQL = "SELECT id,departure_city,departure_date,arrival_city,arrival_date,status " +
            "FROM flight where id = ?;";

    private static final String FIND_ALL_SQL = "SELECT id,departure_city,departure_date,arrival_city,arrival_date,status FROM flight;";



    @Override
    public Flight save(Flight flight) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, flight.getDepartureCity());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(flight.getDepartureDate()));
            preparedStatement.setString(3, flight.getArrivalCity());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(flight.getArrivalDate()));
            preparedStatement.setString(5, flight.getStatus());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                flight.setId(generatedKeys.getLong("id"));
            }
            return flight;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Flight update(Flight flight) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(UPDATE_SQL);) {
            preparedStatement.setString(1, flight.getDepartureCity());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(flight.getDepartureDate()));
            preparedStatement.setString(3, flight.getArrivalCity());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(flight.getArrivalDate()));
            preparedStatement.setString(5, flight.getStatus());
            preparedStatement.setLong(6, flight.getId());
            preparedStatement.executeUpdate();
            return flight;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Flight flight = null;
            if(resultSet.next()){
                flight = buildFlight(resultSet);
            }
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Flight> findAll() {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()) {
                Flight flight = buildFlight(resultSet);
                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Flight> findAllPrices() {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Flight> flightList = new ArrayList<>();
            while (resultSet.next()) {
                Flight flight = buildFlightPrices(resultSet);
                flightList.add(flight);
            }
            return flightList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Flight> findAllTicketPrices() {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            List<Flight> flightList = new ArrayList<>();
            while (resultSet.next()) {
                Flight flight = buildFlightTicketPrices(resultSet);
                flightList.add(flight);
            }
            return flightList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(DELETE_SQL);) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Flight buildFlight(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();
        flight.setId(resultSet.getLong("id"));
        flight.setDepartureCity(resultSet.getString("departure_city"));
        flight.setDepartureDate(resultSet.getTimestamp("departure_date").toLocalDateTime());
        flight.setArrivalCity(resultSet.getString("arrival_city"));
        flight.setArrivalDate(resultSet.getTimestamp("arrival_date").toLocalDateTime());
        flight.setStatus(resultSet.getString("status"));
        return flight;
    }

    public Flight buildFlightPrices(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();
        flight.setId(resultSet.getLong("id"));
        flight.setDepartureCity(resultSet.getString("departure_city"));
        flight.setDepartureDate(resultSet.getTimestamp("departure_date").toLocalDateTime());
        flight.setArrivalCity(resultSet.getString("arrival_city"));
        flight.setArrivalDate(resultSet.getTimestamp("arrival_date").toLocalDateTime());
        flight.setStatus(resultSet.getString("status"));
        flight.setPrices(priceRepository.findAllFlights(resultSet.getLong("id")));
        return flight;
    }

    public Flight buildFlightTicketPrices(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();
        flight.setId(resultSet.getLong("id"));
        flight.setDepartureCity(resultSet.getString("departure_city"));
        flight.setDepartureDate(resultSet.getTimestamp("departure_date").toLocalDateTime());
        flight.setArrivalCity(resultSet.getString("arrival_city"));
        flight.setArrivalDate(resultSet.getTimestamp("arrival_date").toLocalDateTime());
        flight.setStatus(resultSet.getString("status"));
        flight.setTickets(ticketRepository.findAllTicketPrice(resultSet.getLong("id")));
        return flight;
    }
}
