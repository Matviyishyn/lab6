package org.gift.backoffice.core.manager;

import org.gift.backoffice.model.user.AbstractUserEntity;
import org.gift.common.dto.CustomerDto;
import org.gift.common.dto.ShopDto;
import org.gift.common.dto.UserDto;
import org.gift.common.status.AccessLevel;

import java.util.NoSuchElementException;

public interface RegistrationManager {
    AbstractUserEntity initUserEntity();

    boolean registerCustomer(CustomerDto customer);

    boolean registerShop(ShopDto shop);

    AccessLevel logIn(UserDto user);

    boolean logOut();

    boolean verifyUserRegistration(String email, String password) throws NoSuchElementException;

    boolean verifyShopRegistration(String email, String password) throws NoSuchElementException;

    String errorMessage();
}
