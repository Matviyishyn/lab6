package org.gift.backoffice.core.impl;

import org.gift.backoffice.model.item.CandyEntity;
import org.gift.backoffice.model.item.SweetEntity;
import org.gift.common.status.ItemCategory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterImplTest {

    @Test
    void filterBySugar() {
        var filterManager = new FilterImpl();
        var list = new LinkedList<SweetEntity>();
        list.add(new CandyEntity(0, 1, ItemCategory.BOX.name(), 2.3,  true,
                "", "", 24.3, "","", new ArrayList<>(), 23.1, 23.1));
        assertEquals(new LinkedList<>(), filterManager.filterBySugar(list, 40, 50));
    }

    @Test
    void sortByPrice() {
        var filterManager = new FilterImpl();
        var list = new LinkedList<SweetEntity>();
        CandyEntity candyEntity1 = new CandyEntity(0, 1, ItemCategory.BOX.name(), 2.3, true,
                "", "", 24.3, "", "", new ArrayList<>(), 23, 23.1);
        list.add(candyEntity1);
        CandyEntity candyEntity2 = new CandyEntity(0, 1, ItemCategory.BOX.name(), 2.3, true,
                "", "", 24.3, "", "", new ArrayList<>(), 23, 100);
        list.add(candyEntity2);
        var exp = new LinkedList<SweetEntity>();
        exp.add(candyEntity2);
        exp.add(candyEntity1);
        assertEquals(exp, filterManager.sortByPrice(list, true));
    }
}