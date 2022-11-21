package org.gift.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gift.backoffice.model.AbstractEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractItemEntity extends AbstractEntity {
    private long shopId;
    private double price;
    private double weight;
    private boolean isAvailable;
    private String category;
    private String label;
    private String description;

    public AbstractItemEntity(long id, long shopId, String category, double price, double weight, boolean isAvailable, String label, String description) {
        super(id);
        this.shopId = shopId;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.isAvailable = isAvailable;
        this.label = label;
        this.description = description;
    }

    //private double sugar;

}
