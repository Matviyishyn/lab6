package org.gift.backoffice.core.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.backoffice.core.manager.RegistrationManager;
import org.gift.backoffice.dao.user_dao.CustomerDao;
import org.gift.backoffice.dao.user_dao.ShopDao;
import org.gift.backoffice.dao.user_dao.UserDao;
import org.gift.backoffice.model.user.AbstractUserEntity;
import org.gift.backoffice.model.user.CustomerEntity;
import org.gift.backoffice.model.user.ShopEntity;
import org.gift.common.dto.CustomerDto;
import org.gift.common.dto.ShopDto;
import org.gift.common.dto.UserDto;
import org.gift.common.status.AccessLevel;

import java.util.NoSuchElementException;
import java.util.Optional;

public class RegistrationImpl implements RegistrationManager {
    private static final Logger LOG = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "Registration fail";

    private CustomerDao customerDao;
    private ShopDao shopDao;
    private UserDao userDao;

    private AbstractUserEntity currentUser;

    public boolean isUserLogged() {
        return currentUser != null;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setCurrentUser(AbstractUserEntity currentUser) {
        this.currentUser = currentUser;
    }

    public RegistrationImpl() {
        currentUser = null;
        shopDao = new ShopDao();
        customerDao = new CustomerDao();
        userDao = new UserDao();
    }

    public void setShopDao(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    public AbstractUserEntity getCurrentUser() {
        return currentUser;
    }

    @Override
    public AbstractUserEntity initUserEntity() {
        LOG.info("AbstractUserEntity initUserEntity: {}", currentUser);
        switch (currentUser.getAccessLevel()) {
            case CUSTOMER:
                Optional<CustomerEntity> customerInfo = customerDao.get(currentUser.getId());
                if (customerInfo.isPresent()) {
                    return initCustomer(customerInfo.get());
                }
            case ADMIN:
            case SHOP:
                Optional<ShopEntity> shopInfo = shopDao.get(currentUser.getId());
                if (shopInfo.isPresent()) {
                    return initShop(shopInfo.get());
                }
            case UNKNOWN:
            default:
                return currentUser;
        }
    }

    private ShopEntity initShop(ShopEntity shopInfo) {
        LOG.info("ShopEntity initShop: ShopEntity={}", shopInfo);
        if (currentUser.getId() != shopInfo.getId()) {
            throw new NoSuchElementException("Incorrect user relation's data from database");
        }
        return new ShopEntity(currentUser.getId(), currentUser.getIsVerified(),
                currentUser.getEmail(), currentUser.getPassword(),
                currentUser.getAccessLevel(), shopInfo.getCountryId(),
                shopInfo.getStatisticId(), shopInfo.getBrand(),
                shopInfo.getStatus(), shopInfo.getShopDescription());
    }

    private CustomerEntity initCustomer(CustomerEntity customerInfo) {
        LOG.info("CustomerEntity initCustomer: CustomerEntity={}", customerInfo);
        if (currentUser.getId() != customerInfo.getId()) {
            throw new NoSuchElementException("Incorrect user relation's data from database");
        }
        return new CustomerEntity(currentUser.getId(), currentUser.getIsVerified(),
                currentUser.getEmail(), currentUser.getPassword(),
                currentUser.getAccessLevel(), customerInfo.getStatus(),
                customerInfo.getCustomerIdentity());
    }

    @Override
    public boolean registerCustomer(CustomerDto customer) {
        LOG.info("boolean registerCustomer: CustomerDto={}", customer);
        //verifyUserRegistration(customer.getEmail(), customer.getPassword());
        if (!customer.validate()) {
            throw new IllegalArgumentException("DTO validation failed");
        }
        customerDao.save(new CustomerEntity(customer.isVerified(), customer.getEmail(),
                customer.getPassword(), customer.getAccessLevel(), customer.getStatus(),
                customer.getName(), customer.getSurname(),
                customer.getPhone(), customer.getAddress()));
        return true;
    }

    @Override
    public boolean registerShop(ShopDto shop) {
        LOG.info("boolean registerShop: ShopDto={}", shop);
        //verifyShopRegistration(shop.getEmail(), shop.getPassword());
        if (!shop.validate()) {
            throw new IllegalArgumentException("DTO validation failed");
        }
        shopDao.save(new ShopEntity(0, shop.isVerified(),
                shop.getEmail(), shop.getPassword(), shop.getAccessLevel(),
                shop.getCountryId(), shop.getStatisticId(),
                shop.getBrand(), shop.getStatus(), shop.getShopDescription()));
        return true;
    }

    @Override
    public AccessLevel logIn(UserDto user) {
        LOG.info("AccessLevel logIn: UserDto={}", user);
        var userEntity = userDao.get(user.getEmail());
        if (userEntity.isPresent()) {
            if (!verifyUser(userEntity.get().getPassword(), user.getPassword())) {
                throw new NoSuchElementException("Incorrect password");
            }
            currentUser = userEntity.get();
            return currentUser.getAccessLevel();
        }
        return null;
    }

    @Override
    public boolean logOut() {
        LOG.info("boolean logOut: currentUser={}", currentUser);
        currentUser = null;
        return true;
    }

    private boolean verifyUser(String userPassword, String password) {
        return userPassword.equals(password);
    }

    @Override
    public boolean verifyUserRegistration(String email, String password) throws NoSuchElementException {
        LOG.info("boolean verifyUserRegistration: email={}, password={}", email, password);
        if (customerDao.getByEmail(email).isEmpty()) {
            throw new NoSuchElementException("Email existed");
        }
        if (customerDao.getByPassword(password).isEmpty()) {
            throw new NoSuchElementException("Password existed");
        }
        return true;
    }

    @Override
    public boolean verifyShopRegistration(String email, String password) throws NoSuchElementException {
        LOG.info("boolean verifyShopRegistration: email={}, password={}", email, password);
        if (shopDao.getByEmail(email).isEmpty()) {
            throw new NoSuchElementException("Email existed");
        }
        if (shopDao.getByPassword(password).isEmpty()) {
            throw new NoSuchElementException("Password existed");
        }
        return true;
    }

    @Override
    public String errorMessage() {
        return ERROR_MESSAGE;
    }
}
