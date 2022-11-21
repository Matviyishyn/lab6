package org.gift.backoffice.model.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gift.backoffice.model.AbstractEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderEntity extends AbstractEntity {

    //Generate uuid
    private long customerId;
    private long shopId;
    private double price;
    private String orderDetails;

    public OrderEntity(long id, long customerId, long shopId, double price, String orderDetails) {
        super(id);
        this.customerId = customerId;
        this.shopId = shopId;
        this.price = price;
        this.orderDetails = orderDetails;
    }
}
