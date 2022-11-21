package org.gift.backoffice.dao.item_dao;

import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.model.item.BoxEntity;

import java.util.List;
import java.util.Optional;

public class BoxDao implements Dao<BoxEntity, Long> {
    @Override
    public Optional<BoxEntity> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<BoxEntity> getAll() {
        return null;
    }

    @Override
    public void save(BoxEntity abstractItemEntity) {

    }

    @Override
    public void update(BoxEntity abstractItemEntity, String[] params) {

    }

    @Override
    public void delete(BoxEntity abstractItemEntity) {

    }
}
