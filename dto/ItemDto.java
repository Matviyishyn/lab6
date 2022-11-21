package org.gift.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private long itemMetaDataId;
    private long shopId;
    private double price;
    private double weight;
    private boolean isAvailable;
    private String category;
    private String label;
    private String description;
}
