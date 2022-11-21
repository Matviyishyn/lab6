package org.gift.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChocolateEntity extends SweetEntity {
    public ChocolateEntity(long id, long shopId, String categoryId, double price, double weight, boolean isAvailable, String label, String description, double sugar, String brand, String chocolateType, List<String> components) {
        super(id, shopId, categoryId, price, weight, isAvailable, label, description, sugar, brand, chocolateType, components);
    }
}
