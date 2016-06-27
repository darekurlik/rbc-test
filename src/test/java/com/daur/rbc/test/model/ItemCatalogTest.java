package com.daur.rbc.test.model;

import org.junit.Test;

import java.util.Properties;

import static org.fest.assertions.Assertions.assertThat;

public class ItemCatalogTest {

    @Test
    public void ofValidProperties_whenItemNameEmpty_thenIgnore() {
        ItemCatalog actual = ItemCatalog.ofValidProperties(new Properties() {{
            put("", "1.0");
            put("apple", "1.2");
        }});

        assertThat(actual.getItems()).containsOnly(new Item("apple", 1.2));
    }

    @Test
    public void ofValidProperties_whenInvalidValue_thenIgnore() {
        ItemCatalog actual = ItemCatalog.ofValidProperties(new Properties() {{
            put("banana", "");
            put("apple", "1.2");
        }});

        assertThat(actual.getItems()).containsOnly(new Item("apple", 1.2));
    }
}