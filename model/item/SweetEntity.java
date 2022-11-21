package org.gift.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class SweetEntity extends AbstractItemEntity {
    private double sugar;
    private String brand;
    private String chocolateType;
    private List<String> components;

    public SweetEntity(long id, long shopId, String categoryId, double price, double weight, boolean isAvailable, String label, String description, double sugar, String brand, String chocolateType, List<String> components) {
        super(id, shopId, categoryId, price, weight, isAvailable, label, description);
        this.sugar = sugar;
        this.brand = brand;
        this.chocolateType = chocolateType;
        this.components = components;
    }
}
