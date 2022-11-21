package org.gift.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoxDto extends ItemDto {
    private int colorCode;
    private double scale;
    private double width;
    private double length;
    private String color;

    public BoxDto(long shopId, double price, double weight, boolean isAvailable, String category, String label, String description, int colorCode, double scale, double width, double length, String color) {
        super(0, shopId, price, weight, isAvailable, category, label, description);
        this.colorCode = colorCode;
        this.scale = scale;
        this.width = width;
        this.length = length;
        this.color = color;
    }
}
