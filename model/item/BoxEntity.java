package org.gift.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoxEntity extends AbstractItemEntity {
    private int colorCode;
    private double scale;
    private double width;
    private double length;
    private String color;

    public BoxEntity(long id, long shopId, String categoryId, double price, double weight, boolean isAvailable, String label, String description, int colorCode, double scale, double width, double length, String color) {
        super(id, shopId, categoryId, price, weight, isAvailable, label, description);
        this.colorCode = colorCode;
        this.scale = scale;
        this.width = width;
        this.length = length;
        this.color = color;
    }
}
