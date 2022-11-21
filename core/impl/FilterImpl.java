package org.gift.backoffice.core.impl;

import org.gift.backoffice.core.manager.FilterManager;
import org.gift.backoffice.model.item.AbstractItemEntity;
import org.gift.backoffice.model.item.SweetEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterImpl implements FilterManager {
    @Override
    public boolean filterById() {
        return false;
    }

    @Override
    public boolean filterByTitle() {
        return false;
    }

    @Override
    public List<SweetEntity> filterBySugar(List<SweetEntity> list, double min, double max) {
        return list.stream()
                .filter(e -> (e.getSugar() >= min && e.getSugar() <= max))
                .collect(Collectors.toList());
    }

    @Override
    public boolean filterByCategory() {
        return false;
    }

    @Override
    public List<SweetEntity> sortByPrice(List<SweetEntity> list, boolean isReverse) {

        if (isReverse) {
            return list.stream()
                    .sorted(Comparator.comparing(AbstractItemEntity::getPrice).reversed())
                    .collect(Collectors.toList());
        } else {
            return list.stream()
                    .sorted(Comparator.comparing(AbstractItemEntity::getPrice))
                    .collect(Collectors.toList());
        }

    }
}
