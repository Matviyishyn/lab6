package org.gift.backoffice.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerIdentityEntity {
    private String name;
    private String surname;
    private String phone;
    private String address;
}
