package org.gift.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CandyEntity extends SweetEntity {
    private double amount;
    private double priceByOne;

    public CandyEntity(long id, long shopId, String categoryId, double weight, boolean isAvailable, String label, String description, double sugar, String brand, String chocolateType, List<String> components, double amount, double priceByOne) {
        super(id, shopId, categoryId, amount * priceByOne, weight, isAvailable, label, description, sugar, brand, chocolateType, components);
        this.amount = amount;
        this.priceByOne = priceByOne;
    }
}
