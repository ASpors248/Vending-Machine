package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends Items{

    public Drinks(String name, BigDecimal price, String itemType, int itemsLeft) {
        super(name, price, itemType, itemsLeft);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setPrice(BigDecimal price) {
        super.setPrice(price);
    }

    @Override
    public int getItemsLeft() {
        return super.getItemsLeft();
    }

    @Override
    public void setItemsLeft(int itemsLeft) {
        super.setItemsLeft(itemsLeft);
    }




}


