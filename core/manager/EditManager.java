package org.gift.backoffice.core.manager;

import org.gift.common.dto.ItemDto;

public interface EditManager {
    boolean createNewItem(ItemDto template);
    boolean editItem(ItemDto template, String... parameters);
}
