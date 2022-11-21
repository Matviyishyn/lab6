package org.gift.backoffice.core.impl;

import org.gift.backoffice.core.data_structures.Package;
import org.gift.backoffice.model.item.BoxEntity;
import org.gift.common.status.ItemCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageImplTest {
    //Test demo as there are a lot of logs
    @Test
    void formPackage() {
        var packageManager = new PackageImpl();
        var box = new BoxEntity(0, 1, ItemCategory.BOX.name(), 2.3, 2.4, true,
                "", "", 0x000000, 2.3, 2.4, 23.1, "");
        var p = new Package(box, null);
        var exp = new Package(null, null);
        packageManager.setUserPackage(exp);
        assertEquals(p, packageManager.editPackage(box));
    }

    @Test
    void editPackage() {
        var packageManager = new PackageImpl();
        var box = new BoxEntity(0, 1, ItemCategory.BOX.name(), 2.3, 2.4, true,
                "", "", 0x000000, 2.3, 2.4, 23.1, "");
        var p = new Package(box, null);
        var exp = new Package(null, null);
        packageManager.setUserPackage(exp);
        assertEquals(p, packageManager.editPackage(box));
    }

    @Test
    void viewCard() {
        var packageManager = new PackageImpl();
        var pack = new Package();
        packageManager.setUserPackage(pack);
        assertEquals(pack, packageManager.viewCard());
    }

    @Test
    void findItem() {
        var packageManager = new PackageImpl();
        assertFalse(packageManager.findItem());
    }
}