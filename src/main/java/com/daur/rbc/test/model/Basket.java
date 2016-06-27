package com.daur.rbc.test.model;

import java.util.List;

public class Basket {
    private final List<BasketItem> basketItems;

    public Basket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public double calculateCost() {
        double result = 0.0;
        for (BasketItem each : basketItems) {
            result += each.getPrice() * (double) each.getCount();
        }
        return result;
    }

    public List<BasketItem> getItems() {
        return basketItems;
    }
}
