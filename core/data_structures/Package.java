package org.gift.backoffice.core.data_structures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gift.backoffice.model.item.BoxEntity;
import org.gift.backoffice.model.item.SweetEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    private BoxEntity box;
    private List<SweetEntity> sweets;
}