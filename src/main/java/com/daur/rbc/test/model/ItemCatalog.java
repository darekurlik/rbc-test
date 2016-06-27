package com.daur.rbc.test.model;

import java.util.*;

public class ItemCatalog {

    private List<Item> items = new ArrayList<>();

    static ItemCatalog ofValidProperties(Properties properties) {
        ItemCatalog result = new ItemCatalog();
        properties.forEach((key, value) -> {
            if (isItemValid(key, value)) {
                result.add(key, value);
            }
        });
        return result;
    }

    private static boolean isItemValid(Object key, Object value) {
        return isKeyValid(key) && isValueValid(value);
    }

    private static boolean isKeyValid(Object rawKey) {
        String key = String.valueOf(rawKey);
        return !key.isEmpty();
    }

    private static boolean isValueValid(Object rawValue) {
        try {
            Double.parseDouble(String.valueOf(rawValue));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void add(Object key, Object value) {
        items.add(new Item(key.toString(), Double.valueOf(value.toString())));
    }

    public List<Item> getItems() {
        return items;
    }
}
