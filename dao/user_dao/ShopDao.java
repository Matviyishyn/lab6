package org.gift.backoffice.dao.user_dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.dao.JDBCProcessor;
import org.gift.backoffice.model.user.ShopEntity;
import org.gift.common.status.AccessLevel;
import org.gift.common.status.ShopStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopDao implements Dao<ShopEntity, Long> {
    private static final Logger LOG = LogManager.getLogger();
    private static final JDBCProcessor JDBC = new JDBCProcessor();

    private static final String GET_SHOP = "select 1 from ";
    private static final String GET_ALL_SHOP = "select * from shop inner join user_metadata um on shop.user_metadata_id = um.id";
    private static final String SAVE_SHOP = "insert into shop (country_id, statistic_id, brand, shop_description, user_metadata_id) VALUES (?, ?, ?, ?, (select id from user_metadata order by id desc limit 1))";
    private static final String SAVE_SHOP_METADATA = "insert into user_metadata (email, password, is_verified) values (?, ?, ?)";
    private static final String SAVE_SHOP_COUNTRY = "insert into country (label) VALUES (?)";
    private static final String SAVE_SHOP_STATISTIC = "insert into statistic (id) values (?)";
    private static final String UPDATE_SHOP = "";

    @Override
    public Optional<ShopEntity> get(Long id) {
        try(Connection connection = JDBC.getConnection();
            Statement statement = connection.createStatement()) {
            var result = statement.executeQuery(GET_SHOP);
            if (result.next()) {
                return Optional.of(initShop(result));
            }
        } catch (SQLException ex) {
            LOG.error("Get by id failure", ex);
        }
        return Optional.empty();
    }

    @Override
    public List<ShopEntity> getAll() {
        List<ShopEntity> shops = new ArrayList<>();
        try(Connection connection = JDBC.getConnection();
        Statement statement = connection.createStatement()) {
            var result = statement.executeQuery(GET_ALL_SHOP);
            while (result.next()) {
                shops.add(initShop(result));
            }
        } catch (SQLException ex) {
            LOG.error("Get all failure", ex);
        }
        return shops;
    }

    private ShopEntity initShop(ResultSet result) throws SQLException {
        return new ShopEntity(result.getLong("id"),
                result.getBoolean("is_verified"),
                result.getString("email"),
                result.getString("password"),
                AccessLevel.valueOf(result.getString("access_level")),
                result.getLong("country_id"),
                result.getLong("statistic_id"),
                result.getString("brand"),
                ShopStatus.valueOf(result.getString("status")),
                result.getString("shop_description"));
    }

    public Optional<ShopEntity> getByEmail(String email) {
        return Optional.empty();
    }

    public Optional<ShopEntity> getByPassword(String password) {
        return Optional.empty();
    }

    @Override
    public void save(ShopEntity shopEntity) {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement statementShop = connection.prepareStatement(SAVE_SHOP);
            PreparedStatement statementMetaData = connection.prepareStatement(SAVE_SHOP_METADATA)) {
            int index = 1;
            statementMetaData.setString(index++, shopEntity.getEmail());
            statementMetaData.setString(index++, shopEntity.getPassword());
            statementMetaData.setBoolean(index++, shopEntity.getIsVerified());
            if (!statementMetaData.execute()) {
                throw new SQLException("Can't save user meta data");
            }
            index = 2;
            statementShop.setLong(index++, 1);
            statementShop.setLong(index++, 1);
            statementShop.setString(index++, shopEntity.getBrand());
            statementShop.setString(index++, shopEntity.getShopDescription());
            if (!statementShop.execute()) {
                throw new SQLException("Can't save user");
            }
        } catch (SQLException ex) {
            LOG.error("save failure", ex);
        }
    }

    @Override
    public void update(ShopEntity shopEntity, String[] params) {

    }

    @Override
    public void delete(ShopEntity shopEntity) {

    }
}
