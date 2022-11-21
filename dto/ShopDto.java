package org.gift.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.gift.common.status.AccessLevel;
import org.gift.common.status.ShopStatus;

@Data
@AllArgsConstructor
public class ShopDto {
    private boolean isVerified;
    private String email;
    private String password;
    private AccessLevel accessLevel;
    private long countryId;
    private long statisticId;
    private String brand;
    private ShopStatus status;
    private String shopDescription;

    public boolean validate() {
        if (accessLevel == null || accessLevel != AccessLevel.SHOP) {
            return false;
        }
        if (isValidString(email)) {
            return false;
        }
        if (countryId <= 0) {
            return false;
        }
        if (status == ShopStatus.BANNED || status == ShopStatus.INACTIVE) {
            return false;
        }
        return true;
    }

    private static boolean isValidString(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }
}
