package org.gift.backoffice.dao.item_dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.dao.JDBCProcessor;
import org.gift.backoffice.model.item.AbstractItemEntity;
import org.gift.backoffice.model.item.BoxEntity;
import org.gift.common.status.ItemCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//Addition table category_id to table name (e.g. 1 == box)
//and select with join + use table name to choice table
public class ItemMedaDataDao implements Dao<AbstractItemEntity, Long> {
    private static final Logger LOG = LogManager.getLogger();
    private static final JDBCProcessor JDBC = new JDBCProcessor();

    private static final String GET_ITEM_META_DATA = "select * from item_common_data where id = ?";
    private static final String GET_ITEM_CATEGORY = "select name from item_category where id = ?";
    @Override
    public Optional<AbstractItemEntity> get(Long id) {
        LOG.info("Optional<AbstractItemEntity> get: Long id:{}", id);
        try (Connection connection = JDBC.getConnection();
             PreparedStatement statementCategory = connection.prepareStatement(GET_ITEM_CATEGORY);
             PreparedStatement statement = connection.prepareStatement(GET_ITEM_META_DATA)){
            statement.setLong(1, id);
            var resultItem = statement.executeQuery();
            if (!resultItem.next()) {
                throw new SQLException("No item by id");
            }
            statementCategory.setLong(1, resultItem.getLong("category_id"));
            var resultCategory = statementCategory.executeQuery();
            if (!resultCategory.next()) {
                throw new SQLException("No category by id");
            }
            switch (ItemCategory.valueOf(resultCategory.getString("name"))) {
                case BOX:
                    var box = new BoxEntity(resultItem.getLong("id"), resultItem.getLong((int) resultItem.getLong("shop_id")),
                            ItemCategory.valueOf(resultCategory.getString("name")).getValue(), resultItem.getDouble("price"),
                            resultItem.getDouble("weight"), resultItem.getBoolean("is_available"),
                            resultItem.getString("label"), resultItem.getString("description"),
                            0x00ffaaa, 100.0, 200.3, 432.2, "black");
                case CANDY: {

                    break;
                }
                case COOKIE: {

                    break;
                }
                case CHOCOLATE: {

                    break;
                }
                default:
                    throw new SQLException("No one available category");
            }
        } catch (SQLException ex) {
            LOG.error("Can't get data from database", ex);
        }
        return Optional.empty();
    }

    @Override
    public List<AbstractItemEntity> getAll() {
        return null;
    }

    @Override
    public void save(AbstractItemEntity abstractItemEntity) {

    }

    @Override
    public void update(AbstractItemEntity abstractItemEntity, String[] params) {

    }

    @Override
    public void delete(AbstractItemEntity abstractItemEntity) {

    }
}
