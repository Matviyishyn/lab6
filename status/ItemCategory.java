package org.gift.common.status;

public enum ItemCategory {
    //TODO REPLACE CHOCOLATE BY ANOTHER TYPE (e.g. flowers etc.);
    BOX("Box"), CHOCOLATE("Chocolate"), COOKIE("Cookie"), CANDY("Candy");

    private final String value;

    ItemCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
