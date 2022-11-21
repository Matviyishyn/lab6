package org.gift.backoffice.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.common.status.AccessLevel;
import org.gift.common.status.ShopStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ShopEntity extends AbstractUserEntity {
    private long countryId;
    private long statisticId;
    private String brand;
    private ShopStatus status;
    private String shopDescription;

    public ShopEntity(long id, boolean isVerified, String email,
                      String password, AccessLevel accessLevel,
                      long countryId, long statisticId,
                      String brand, ShopStatus status, String shopDescription) {
        super(id, isVerified, email, password, accessLevel);
        this.countryId = countryId;
        this.statisticId = statisticId;
        this.brand = brand;
        this.status = status;
        this.shopDescription = shopDescription;
    }
}
