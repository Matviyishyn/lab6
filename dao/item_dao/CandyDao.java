package org.gift.backoffice.dao.item_dao;

import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.model.item.CandyEntity;

import java.util.List;
import java.util.Optional;

public class CandyDao implements Dao<CandyEntity, Long> {
    @Override
    public Optional<CandyEntity> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CandyEntity> getAll() {
        return null;
    }

    @Override
    public void save(CandyEntity abstractItemEntity) {

    }

    @Override
    public void update(CandyEntity abstractItemEntity, String[] params) {

    }

    @Override
    public void delete(CandyEntity abstractItemEntity) {

    }
}
