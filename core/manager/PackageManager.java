package org.gift.backoffice.core.manager;

import org.gift.backoffice.model.item.AbstractItemEntity;
import org.gift.backoffice.core.data_structures.Package;
import org.gift.backoffice.model.item.SweetEntity;

import java.util.List;

public interface PackageManager {
    void updateStatistic(AbstractItemEntity abstractItem);
    long findItemIdByLabel(String label);
    Package formPackage(List<AbstractItemEntity> items);
    Package editPackage(AbstractItemEntity item);
    Package viewCard();
    boolean findItem();
    boolean deleteItemFromPackage(List<SweetEntity> card, long id);
    AbstractItemEntity getItemById(long id);
}
