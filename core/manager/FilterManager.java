package org.gift.backoffice.core.manager;

import org.gift.backoffice.model.item.SweetEntity;

import java.util.List;

public interface FilterManager {
    boolean filterById();
    boolean filterByTitle();
    List<SweetEntity> filterBySugar(List<SweetEntity> list, double min, double max);
    boolean filterByCategory();
    List<SweetEntity> sortByPrice(List<SweetEntity> list, boolean isReverse);
}
