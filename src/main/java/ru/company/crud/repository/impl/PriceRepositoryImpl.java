package ru.company.crud.repository.impl;

import org.springframework.stereotype.Repository;
import ru.company.crud.entity.Price;
import ru.company.crud.exception.DaoException;
import ru.company.crud.repository.PriceRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepository {
    DataSource dataSource;

    public PriceRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SAVE_SQL = "INSERT INTO price (status_class, cost, flight_id) VALUES (?,?,?);";

    private static final String UPDATE_SQL = "UPDATE price SET status_class = ?, cost = ? WHERE id = ?;";

    private static final String FIND_BY_ID_SQL = "SELECT * FROM price where id = ?;";

    private static final String FIND_ALL_SQL = "SELECT * FROM price;";

    private static final String FIND_ALL_FLIGHTS_ID_SQL = "SELECT * FROM price where flight_id = ?;";

    private static final String DELETE_SQL = "DELETE FROM price WHERE id = ?;";

    @Override
    public Price save(Price price) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1,price.getStatusClass());
            preparedStatement.setBigDecimal(2, price.getCost());
            preparedStatement.setLong(3, price.getFlight().getId());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                price.setId(generatedKeys.getLong("id"));
            }
            return price;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Price update(Price price) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(UPDATE_SQL);) {
            preparedStatement.setString(1, price.getStatusClass());
            preparedStatement.setBigDecimal(2, price.getCost());
            preparedStatement.setLong(3, price.getId());
            preparedStatement.executeUpdate();
            return price;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Price> findById(Long id) {
        try(Connection connection = dataSource.getConnection();) {
            var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Price price = new Price();
            if(resultSet.next()) {
                price = buildPrice(resultSet);
            }
            return Optional.ofNullable(price);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Price> findAll() {
        try (Connection connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            List<Price> prices = new ArrayList<>();
            if(resultSet.next()) {
                Price price = buildPrice(resultSet);
                prices.add(price);
            }
            return prices;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Price> findAllFlights(Long id) {
        try(Connection connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_ALL_FLIGHTS_ID_SQL);) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            List<Price> priceList = new ArrayList<>();
            while (resultSet.next()) {
                Price price = buildPrice(resultSet);
                priceList.add(price);
            }
            return priceList;
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

    public Price buildPrice(ResultSet resultSet) throws SQLException {
        Price price = new Price();
        price.setId(resultSet.getLong("id"));
        price.setStatusClass(resultSet.getString("status_class"));
        price.setCost(resultSet.getBigDecimal("cost"));
        return price;
    }
}
