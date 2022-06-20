package ru.company.crud.repository.impl;

import org.springframework.stereotype.Repository;
import ru.company.crud.entity.Ticket;
import ru.company.crud.exception.DaoException;
import ru.company.crud.repository.PriceRepository;
import ru.company.crud.repository.TicketRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    DataSource dataSource;

    PriceRepository priceRepository;

    public TicketRepositoryImpl(DataSource dataSource, PriceRepository priceRepository) {
        this.dataSource = dataSource;
        this.priceRepository = priceRepository;
    }

    private static final String SAVE_SQL = "INSERT INTO ticket (surname, name, patronymic, seat, flight_id, price_id) VALUES (?,?,?,?,?,?);";

    private static final String FIND_BY_ID_SQL = "SELECT * FROM ticket WHERE id = ?;";

    private static final String UPDATE_SQL = "UPDATE ticket SET surname = ?, name = ?, patronymic = ?, seat = ? where id = ?;";

    private static final String DELETE_SQL = "DELETE FROM ticket WHERE id = ?;";

    private static final String FIND_ALL_SQL = "SELECT * FROM ticket;";

    private static final String FIND_ALL_FLIGHT_ID_SQL = "SELECT * FROM ticket WHERE flight_id = ?;";

    @Override
    public Ticket save(Ticket ticket) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, ticket.getSurname());
            preparedStatement.setString(2, ticket.getName());
            preparedStatement.setString(3, ticket.getPatronymic());
            preparedStatement.setString(4, ticket.getSeat());
            preparedStatement.setLong(5, ticket.getFlight().getId());
            preparedStatement.setLong(6, ticket.getPrice().getId());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                ticket.setId(generatedKeys.getLong("id"));
            }
            return ticket;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(UPDATE_SQL);) {
            preparedStatement.setString(1, ticket.getSurname());
            preparedStatement.setString(2, ticket.getName());
            preparedStatement.setString(3, ticket.getPatronymic());
            preparedStatement.setString(4, ticket.getSeat());
            preparedStatement.setLong(5, ticket.getId());
            preparedStatement.executeUpdate();
            return ticket;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Ticket ticket = new Ticket();
            if(resultSet.next()) {
                ticket = buildTicket(resultSet);
            }
            return Optional.ofNullable(ticket);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<Ticket> findAll() {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            List<Ticket> ticketList = new ArrayList<>();
            if(resultSet.next()) {
                var ticket = buildTicket(resultSet);
                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<Ticket> findAllTicket(Long id) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_ALL_FLIGHT_ID_SQL);) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            List<Ticket> ticketList = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = buildTicket(resultSet);
                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Ticket> findAllTicketPrice(Long id) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_ALL_FLIGHT_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            List<Ticket> ticketList = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = buildTicketPrice(resultSet);
                ticketList.add(ticket);
            }
            return ticketList;
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

    public Ticket buildTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setSurname(resultSet.getString("surname"));
        ticket.setName(resultSet.getString("name"));
        ticket.setPatronymic(resultSet.getString("patronymic"));
        ticket.setSeat(resultSet.getString("seat"));
        return ticket;
    }

    public Ticket buildTicketPrice(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setSurname(resultSet.getString("surname"));
        ticket.setName(resultSet.getString("name"));
        ticket.setPatronymic(resultSet.getString("patronymic"));
        ticket.setSeat(resultSet.getString("seat"));
        ticket.setPrice(priceRepository.findById(resultSet.getLong("price_id")).get());
        return ticket;
    }
}
