package com.daur.rbc.test.main;

import com.daur.rbc.test.model.Basket;
import com.daur.rbc.test.model.BasketLoader;
import com.daur.rbc.test.model.ItemCatalog;
import com.daur.rbc.test.model.ItemCatalogFactory;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RBCTestMain {

    private final ItemCatalogFactory itemCatalogFactory;
    private final BasketLoader basketLoader;

    RBCTestMain(ItemCatalogFactory itemCatalogFactory,
                BasketLoader basketLoader) {
        this.itemCatalogFactory = itemCatalogFactory;
        this.basketLoader = basketLoader;
    }

    public static void main(String[] args) {
        ItemCatalogFactory itemCatalogFactory = new ItemCatalogFactory();
        BasketLoader basketLoader = new BasketLoader();

        String result = new RBCTestMain(itemCatalogFactory, basketLoader).performMainLogic(args);
        System.out.println(result);
    }

    String performMainLogic(String[] args) {
        try {
            ItemCatalog itemCatalog = itemCatalogFactory.create(args);
            Basket basket = basketLoader.load(itemCatalog, System.in, System.out);
            return formatOutput(basket);
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    private String formatOutput(Basket basket) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(basket.calculateCost());
    }
}
