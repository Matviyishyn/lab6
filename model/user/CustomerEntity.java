package org.gift.backoffice.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.common.status.AccessLevel;
import org.gift.common.status.CustomerStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomerEntity extends AbstractUserEntity {
    private CustomerIdentityEntity customerIdentity;
    private CustomerStatus status;

    public CustomerEntity(long id, boolean isVerified, String email,
                          String password, AccessLevel accessLevel,
                          CustomerStatus status,
                          CustomerIdentityEntity customerIdentity) {
        super(id, isVerified, email, password, accessLevel);
        this.status = status;
        this.customerIdentity = customerIdentity;
    }

    public CustomerEntity(boolean isVerified, String email,
                          String password, AccessLevel accessLevel,
                          CustomerStatus status,
                          String name, String surname, String phone, String address) {
        super(0, isVerified, email, password, accessLevel);
        this.status = status;
        this.customerIdentity = new CustomerIdentityEntity(name, surname, phone, address);
    }
}
