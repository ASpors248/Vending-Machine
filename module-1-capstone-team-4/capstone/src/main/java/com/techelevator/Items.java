package com.techelevator;

import java.math.BigDecimal;

public abstract class Items {

    private String name;
    private BigDecimal price;
    private int itemsLeft;
    private String itemType;


    public Items(String name, BigDecimal price, String itemType, int itemsLeft) {
        this.name = name;
        this.price = price;
        this.itemType = itemType;
        this.itemsLeft = 7;

    }

    public String getItemType() {
        return itemType;
    }


    public int getItemsLeft() {
        return itemsLeft;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setItemsLeft(int itemsLeft) {
        this.itemsLeft = itemsLeft;
    }

    @Override
    public String toString() {
        return "Items{" + "name='" + name + '\'' + ", price=" + price + ", itemsLeft=" + itemsLeft + '\'' +
                '}';
    }
}

