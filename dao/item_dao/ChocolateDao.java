package org.gift.backoffice.dao.item_dao;

import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.model.item.ChocolateEntity;

import java.util.List;
import java.util.Optional;

public class ChocolateDao implements Dao<ChocolateEntity, Long> {
    @Override
    public Optional<ChocolateEntity> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ChocolateEntity> getAll() {
        return null;
    }

    @Override
    public void save(ChocolateEntity abstractItemEntity) {

    }

    @Override
    public void update(ChocolateEntity abstractItemEntity, String[] params) {

    }

    @Override
    public void delete(ChocolateEntity abstractItemEntity) {

    }
}
