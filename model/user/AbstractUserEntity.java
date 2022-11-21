package org.gift.backoffice.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.backoffice.model.AbstractEntity;
import org.gift.common.status.AccessLevel;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class AbstractUserEntity extends AbstractEntity {

    private Boolean isVerified;
    private String email;
    private String password;
    private AccessLevel accessLevel;

    public AbstractUserEntity(long id, boolean isVerified, String email, String password, AccessLevel accessLevel) {
        super(id);
        this.isVerified = isVerified;
        this.email = email;
        this.password = password;
        this.accessLevel = accessLevel;
    }

}
