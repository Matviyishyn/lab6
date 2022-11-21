package org.gift.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.gift.common.status.AccessLevel;
import org.gift.common.status.CustomerStatus;

@Data
@AllArgsConstructor
public class CustomerDto {
    private boolean isVerified;
    private AccessLevel accessLevel;
    private CustomerStatus status;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String address;

    public boolean validate() {
        if (accessLevel == null || accessLevel != AccessLevel.CUSTOMER) {
            return false;
        }
        if (!isValidString(email)) {
            return false;
        }
        if (!isValidString(password)) {
            return false;
        }
        if (!isValidString(name)) {
            return false;
        }
        if (!isValidString(surname)) {
            return false;
        }
        if (!isValidString(phone)) {
            return false;
        }
        if (!isValidString(address)) {
            return false;
        }
        return true;
    }

    private static boolean isValidString(String string) {
        return string != null && !string.isEmpty() && !string.isBlank();
    }
}
