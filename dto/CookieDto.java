package org.gift.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CookieDto extends ItemDto{
    private double amount;
    private double priceByOne;
    private double sugar;
    private String form;
    private String brand;
    private String chocolateType;
    private List<String> components;

    public CookieDto(long shopId, double price, double weight, boolean isAvailable, String category, String label, String description, double amount, double priceByOne, double sugar, String form, String brand, String chocolateType, List<String> components) {
        super(0, shopId, price, weight, isAvailable, category, label, description);
        this.amount = amount;
        this.priceByOne = priceByOne;
        this.sugar = sugar;
        this.form = form;
        this.brand = brand;
        this.chocolateType = chocolateType;
        this.components = components;
    }
}
