package com.daur.rbc.test.model;

import org.junit.Test;

import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static org.fest.assertions.Assertions.assertThat;

public class BasketTest {

    @Test
    public void calculateCost_whenBasketIsEmpty_thenReturnZero() {
        Basket underTest = new Basket(emptyList());

        assertThat(underTest.calculateCost()).isEqualTo(0.0);
    }

    @Test
    public void calculateCost_whenBasketWithOneItem_thenItemCostMultipliedByCount() {
        Basket underTest = new Basket(new ArrayList<BasketItem>() {{
            add(BasketItem.of("appple", 1.5, 2));
        }});

        assertThat(underTest.calculateCost()).isEqualTo(3.0);
    }

    @Test
    public void calculateCost_whenBasketWithMultipleItems_thenSumItemCostMultipliedByCount() {
        Basket underTest = new Basket(new ArrayList<BasketItem>() {{
            add(BasketItem.of("appple", 1.5, 2));
            add(BasketItem.of("orange", 2.0, 3));
        }});

        assertThat(underTest.calculateCost()).isEqualTo(9.0);
    }

}