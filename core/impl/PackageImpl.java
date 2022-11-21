package org.gift.backoffice.core.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.backoffice.core.manager.PackageManager;
import org.gift.backoffice.dao.item_dao.ItemMedaDataDao;
import org.gift.backoffice.model.item.AbstractItemEntity;
import org.gift.backoffice.model.item.BoxEntity;
import org.gift.backoffice.model.item.SweetEntity;
import org.gift.backoffice.core.data_structures.Package;

import java.util.List;
import java.util.stream.Collectors;

public class PackageImpl implements PackageManager {
    private static final Logger LOG = LogManager.getLogger();

    private final ItemMedaDataDao ITEM_DAO;
    private Package userPackage;

    public PackageImpl() {
        ITEM_DAO = new ItemMedaDataDao();
    }

    public void setUserPackage(Package userPackage) {
        this.userPackage = userPackage;
    }

    @Override
    public void updateStatistic(AbstractItemEntity abstractItem) {
        //TODO delete as replaced by DAO implementation
    }

    @Override
    public long findItemIdByLabel(String label) {
        //TODO replace by call dao finder with parameters
        LOG.info("long findItemIdByLabel: label={}", label);
        var list = ITEM_DAO.getAll();
        return list.stream().filter(e -> e.getLabel().equals(label)).findFirst().get().getId();
    }

    @Override
    public Package formPackage(List<AbstractItemEntity> items) {
        LOG.info("Package formPackage: List<AbstractItemEntity> items={}", items);
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Empty package");
        }
        if (items.size() <= 1) {
            return userPackage = new Package((BoxEntity) items.get(0), null);
        }
        return userPackage = new Package((BoxEntity) items.get(0), items.stream().map(e -> (SweetEntity) e).collect(Collectors.toList()));
    }

    @Override
    public Package editPackage(AbstractItemEntity item) {
        LOG.info("Package editPackage: AbstractItemEntity item={}", item);
        if (item == null) {
            return userPackage;
        }
        if (item instanceof BoxEntity) {
            userPackage.setBox((BoxEntity) item);
        } else {
            userPackage.getSweets().remove((SweetEntity) item);
            userPackage.getSweets().add((SweetEntity) item);
        }
        return userPackage;
    }

    @Override
    public Package viewCard() {
        LOG.info("Package viewCard: return {}", userPackage);
        return userPackage;
    }

    @Override
    public boolean deleteItemFromPackage(List<SweetEntity> card, long id) {
        LOG.info("boolean deleteItemFromPackage: List<AbstractItemEntity> card={}, long id={}", card, id);
        var item = card.get((int)id);
        if (item != null) {
            card.remove(item);
        }
        return true;
    }

    @Override
    public boolean findItem() {
        return false;
    }

    @Override
    public AbstractItemEntity getItemById(long id) {
        return null;
    }
}
