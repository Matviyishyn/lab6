package org.gift.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChocolateDto extends ItemDto{
    private double sugar;
    private String brand;
    private String chocolateType;
    private List<String> components;

    public ChocolateDto(long shopId, double price, double weight, boolean isAvailable, String category, String label, String description, double sugar, String brand, String chocolateType, List<String> components) {
        super(0, shopId, price, weight, isAvailable, category, label, description);
        this.sugar = sugar;
        this.brand = brand;
        this.chocolateType = chocolateType;
        this.components = components;
    }
}
