package org.gift.backoffice.core.impl;

import org.gift.backoffice.core.manager.RegistrationManager;
import org.gift.backoffice.dao.user_dao.CustomerDao;
import org.gift.backoffice.dao.user_dao.ShopDao;
import org.gift.backoffice.dao.user_dao.UserDao;
import org.gift.backoffice.model.user.CustomerEntity;
import org.gift.backoffice.model.user.CustomerIdentityEntity;
import org.gift.backoffice.model.user.ShopEntity;
import org.gift.common.dto.CustomerDto;
import org.gift.common.dto.ShopDto;
import org.gift.common.dto.UserDto;
import org.gift.common.status.AccessLevel;
import org.gift.common.status.CustomerStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {

    @Test
    void initUserEntity() {
        var registration = new RegistrationImpl();
        var customer = new CustomerEntity();
        var customerDao = Mockito.mock(CustomerDao.class);
        registration.setCustomerDao(customerDao);
        Mockito.when(customerDao.get(0L)).thenReturn(Optional.of(customer));
        customer.setId(0L);
        customer.setIsVerified(true);
        customer.setEmail("");
        customer.setPassword("");
        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setCustomerIdentity(new CustomerIdentityEntity());
        customer.setAccessLevel(AccessLevel.CUSTOMER);
        registration.setCurrentUser(customer);
        assertEquals(customer, registration.initUserEntity());
    }

    @Test
    void registerCustomer() {
        var registration = new RegistrationImpl();
        var customerDto = Mockito.mock(CustomerDto.class);
        var customerDao = Mockito.mock(CustomerDao.class);
        registration.setCustomerDao(customerDao);
        Mockito.when(customerDto.validate()).thenReturn(true);
        assertTrue(registration.registerCustomer(customerDto));
        Mockito.verify(customerDao).save(new CustomerEntity(customerDto.isVerified(), customerDto.getEmail(),
                customerDto.getPassword(), customerDto.getAccessLevel(), customerDto.getStatus(),
                customerDto.getName(), customerDto.getSurname(),
                customerDto.getPhone(), customerDto.getAddress()));
    }

    @Test
    void registerShop() {
        var registration = new RegistrationImpl();
        var shopDto = Mockito.mock(ShopDto.class);
        var shopDao = Mockito.mock(ShopDao.class);
        registration.setShopDao(shopDao);
        Mockito.when(shopDto.validate()).thenReturn(true);
        assertTrue(registration.registerShop(shopDto));
        Mockito.verify(shopDao).save(new ShopEntity(0, shopDto.isVerified(),
                shopDto.getEmail(), shopDto.getPassword(), shopDto.getAccessLevel(),
                shopDto.getCountryId(), shopDto.getStatisticId(),
                shopDto.getBrand(), shopDto.getStatus(), shopDto.getShopDescription()));
    }

    @Test
    void logIn() {
        var registration = new RegistrationImpl();
        var userDto = Mockito.mock(UserDto.class);
        var userDao = Mockito.mock(UserDao.class);
        var customerTest = new CustomerEntity();
        registration.setUserDao(userDao);
        customerTest.setAccessLevel(AccessLevel.CUSTOMER);
        customerTest.setPassword("1234");
        Mockito.when(userDto.getEmail()).thenReturn("test@email.com");
        Mockito.when(userDto.getPassword()).thenReturn("1234");
        Mockito.when(userDao.get("test@email.com")).thenReturn(Optional.of(customerTest));
        assertEquals(AccessLevel.CUSTOMER, registration.logIn(userDto));
    }

    @Test
    void logOut() {
        var registration = new RegistrationImpl();
        assertTrue(registration.logOut());
        assertFalse(registration.isUserLogged());
    }

    @Test
    void verifyUserRegistration() {
        var customerDao = Mockito.mock(CustomerDao.class);
        var registration = new RegistrationImpl();
        var email = "example@gmail.com";
        var pass = "d12345";
        registration.setCustomerDao(customerDao);
        Mockito.when(customerDao.getByEmail(email)).thenReturn(Optional.of(new CustomerEntity()));
        Mockito.when(customerDao.getByPassword(pass)).thenReturn(Optional.of(new CustomerEntity()));
        assertTrue(registration.verifyShopRegistration(email, pass));
    }

    @Test
    void verifyShopRegistration() {
        var shopDao = Mockito.mock(ShopDao.class);
        var registration = new RegistrationImpl();
        var email = "example@gmail.com";
        var pass = "d12345";
        registration.setShopDao(shopDao);
        Mockito.when(shopDao.getByEmail(email)).thenReturn(Optional.of(new ShopEntity()));
        Mockito.when(shopDao.getByPassword(pass)).thenReturn(Optional.of(new ShopEntity()));
        assertTrue(registration.verifyShopRegistration(email, pass));
    }

    @Test
    void errorMessage() {
        var registration = new RegistrationImpl();
        var error = "Registration fail";
        assertEquals(error, registration.errorMessage());
    }

}