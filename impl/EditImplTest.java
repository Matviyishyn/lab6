package org.gift.backoffice.core.impl;

import com.sun.mail.imap.protocol.Item;
import org.gift.backoffice.dao.item_dao.BoxDao;
import org.gift.backoffice.model.item.BoxEntity;
import org.gift.common.dto.BoxDto;
import org.gift.common.dto.ItemDto;
import org.gift.common.status.ItemCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EditImplTest {

    @Test
    void createNewItem() {
        var editor = new EditImpl();
        var boxDao = Mockito.mock(BoxDao.class);
        var itemDto = new BoxDto(1, 2.3, 2.4, true, ItemCategory.BOX.name(),
                "", "", 0x000000, 2.3, 2.4, 23.1, "");
        editor.setBoxDao(boxDao);
        assertTrue(editor.createNewItem(itemDto));
        Mockito.verify(boxDao).save(new BoxEntity(0, itemDto.getShopId(), itemDto.getCategory(),
                itemDto.getPrice(), itemDto.getWeight(), itemDto.isAvailable(),
                itemDto.getLabel(), itemDto.getDescription(), itemDto.getColorCode(),
                itemDto.getScale(), itemDto.getWidth(), itemDto.getLength(), itemDto.getColor()));
    }

    @Test
    void editItem() {
        var editor = new EditImpl();
        var boxDao = Mockito.mock(BoxDao.class);
        var itemDto = new BoxDto(1, 2.3, 2.4, true, ItemCategory.BOX.name(),
                "", "", 0x000000, 2.3, 2.4, 23.1, "");
        editor.setBoxDao(boxDao);
        assertTrue(editor.editItem(itemDto, new String[]{}));
        Mockito.verify(boxDao).update(new BoxEntity(0, itemDto.getShopId(), itemDto.getCategory(),
                itemDto.getPrice(), itemDto.getWeight(), itemDto.isAvailable(),
                itemDto.getLabel(), itemDto.getDescription(), itemDto.getColorCode(),
                itemDto.getScale(), itemDto.getWidth(), itemDto.getLength(), itemDto.getColor()), new String[]{});
    }
}