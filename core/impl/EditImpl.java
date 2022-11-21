package org.gift.backoffice.core.impl;

import org.gift.backoffice.core.manager.EditManager;
import org.gift.backoffice.dao.item_dao.BoxDao;
import org.gift.backoffice.dao.item_dao.CandyDao;
import org.gift.backoffice.dao.item_dao.ChocolateDao;
import org.gift.backoffice.dao.item_dao.CookieDao;
import org.gift.backoffice.model.item.BoxEntity;
import org.gift.backoffice.model.item.CandyEntity;
import org.gift.backoffice.model.item.ChocolateEntity;
import org.gift.backoffice.model.item.CookieEntity;
import org.gift.common.dto.*;
import org.gift.common.status.ItemCategory;

public class EditImpl implements EditManager {

    private BoxDao boxDao;
    private CandyDao candyDao;
    private ChocolateDao chocolateDao;
    private CookieDao cookieDao;

    public EditImpl() {
        boxDao = new BoxDao();
        candyDao = new CandyDao();
        chocolateDao = new ChocolateDao();
        cookieDao = new CookieDao();
    }

    public void setBoxDao(BoxDao boxDao) {
        this.boxDao = boxDao;
    }

    public void setCandyDao(CandyDao candyDao) {
        this.candyDao = candyDao;
    }

    public void setChocolateDao(ChocolateDao chocolateDao) {
        this.chocolateDao = chocolateDao;
    }

    public void setCookieDao(CookieDao cookieDao) {
        this.cookieDao = cookieDao;
    }

    @Override
    public boolean createNewItem(ItemDto template) {
        switch (ItemCategory.valueOf(template.getCategory())) {
            case BOX:
                boxDao.save(createBox(template));
                return true;
            case CHOCOLATE:
                chocolateDao.save(createChocolate(template));
                return true;
            case CANDY:
                candyDao.save(createCandy(template));
                return true;
            case COOKIE:
                cookieDao.save(createCookie(template));
                return true;
            default:
                throw new IllegalArgumentException("Category not found");
        }
    }

    private BoxEntity createBox(ItemDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Empty data");
        }
        var box = (BoxDto) item;
        return new BoxEntity(0, box.getShopId(), box.getCategory(),
                box.getPrice(), box.getWeight(), box.isAvailable(),
                box.getLabel(), box.getDescription(), box.getColorCode(),
                box.getScale(), box.getWidth(), box.getLength(), box.getColor());
    }

    private CandyEntity createCandy(ItemDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Empty data");
        }
        var candy = (CandyDto) item;
        return new CandyEntity(0, candy.getShopId(), candy.getCategory(),
                candy.getWeight(), candy.isAvailable(), candy.getLabel(), candy.getDescription(),
                candy.getSugar(), candy.getBrand(), candy.getChocolateType(), candy.getComponents(),
                candy.getAmount(), candy.getPriceByOne());
    }

    private ChocolateEntity createChocolate(ItemDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Empty data");
        }
        var chocolate = (ChocolateDto) item;
        return new ChocolateEntity(0, chocolate.getShopId(), chocolate.getCategory(),
                chocolate.getPrice(), chocolate.getWeight(), chocolate.isAvailable(),
                chocolate.getLabel(), chocolate.getDescription(), chocolate.getSugar(),
                chocolate.getBrand(), chocolate.getChocolateType(), chocolate.getComponents());
    }

    private CookieEntity createCookie(ItemDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Empty data");
        }
        var cookie = (CookieDto) item;
        return new CookieEntity(0, cookie.getShopId(), cookie.getCategory(),
                cookie.getWeight(), cookie.isAvailable(), cookie.getLabel(),
                cookie.getDescription(), cookie.getSugar(), cookie.getBrand(),
                cookie.getChocolateType(), cookie.getComponents(), cookie.getAmount(),
                cookie.getPriceByOne(), cookie.getForm());
    }

    //Будую дто за шаблоном ентеті і
    //по айтем мета дата знаходжу тип і змінюю обєкт
    @Override
    public boolean editItem(ItemDto template, String... parameters) {
        switch (ItemCategory.valueOf(template.getCategory())) {
            case BOX:
                boxDao.update(createBox(template), parameters);
                return true;
            case CHOCOLATE:
                chocolateDao.update(createChocolate(template), parameters);
                return true;
            case CANDY:
                candyDao.update(createCandy(template), parameters);
                return true;
            case COOKIE:
                cookieDao.update(createCookie(template), parameters);
                return true;
            default:
                throw new IllegalArgumentException("Category not found");
        }
    }
    //    public boolean editItem(long id, ItemCategory category) {
//        return false;
//    }
}
