package org.gift.backoffice.dao.item_dao;

import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.model.item.CookieEntity;

import java.util.List;
import java.util.Optional;

public class CookieDao implements Dao<CookieEntity, Long> {
    @Override
    public Optional<CookieEntity> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CookieEntity> getAll() {
        return null;
    }

    @Override
    public void save(CookieEntity abstractItemEntity) {

    }

    @Override
    public void update(CookieEntity abstractItemEntity, String[] params) {

    }

    @Override
    public void delete(CookieEntity abstractItemEntity) {

    }
}
