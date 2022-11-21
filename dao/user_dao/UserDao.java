package org.gift.backoffice.dao.user_dao;

import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.dao.JDBCProcessor;
import org.gift.backoffice.model.user.AbstractUserEntity;
import org.gift.backoffice.model.user.CustomerEntity;
import org.gift.backoffice.model.user.ShopEntity;
import org.gift.common.status.AccessLevel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<AbstractUserEntity, String> {
    private static final String QUERY_GET_BY_EMAIL = "select * from user_metadata where email = ?";

    private final JDBCProcessor JDBC;

    public UserDao() {
        JDBC = new JDBCProcessor();
    }

    @Override
    public Optional<AbstractUserEntity> get(String id) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement((QUERY_GET_BY_EMAIL))) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                switch (AccessLevel.SHOP) {
                    case CUSTOMER:
                        var customer = new CustomerEntity();
                        customer.setId(resultSet.getLong("id"));
                        customer.setIsVerified(resultSet.getBoolean("is_verified"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setPassword(resultSet.getString("password"));
                        customer.setAccessLevel(AccessLevel.CUSTOMER);
                        return Optional.of(customer);
                    case SHOP:
                        var shop = new ShopEntity();
                        shop.setId(resultSet.getLong("id"));
                        shop.setIsVerified(resultSet.getBoolean("is_verified"));
                        shop.setEmail(resultSet.getString("email"));
                        shop.setPassword(resultSet.getString("password"));
                        shop.setAccessLevel(AccessLevel.SHOP);
                        return Optional.of(shop);
                    default:
                        throw new SQLException("User not found");
                }
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    @Override
    public List<AbstractUserEntity> getAll() {
        try (Connection connection = JDBC.getConnection();
             Statement statement = connection.createStatement()) {
            //Here get from database by email
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void save(AbstractUserEntity abstractUserEntity) {

    }

    @Override
    public void update(AbstractUserEntity abstractUserEntity, String[] params) {

    }

    @Override
    public void delete(AbstractUserEntity abstractUserEntity) {

    }
}
