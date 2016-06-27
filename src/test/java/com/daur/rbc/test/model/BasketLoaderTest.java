package com.daur.rbc.test.model;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.System.lineSeparator;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BasketLoaderTest {

    private static final String ITEM_NAME = "apple";
    private static final double ITEM_PRICE = 1.2;

    private final ItemCatalog itemCatalog = mock(ItemCatalog.class);

    private final BasketLoader underTest = new BasketLoader();

    @Before
    public void setUp() throws Exception {
        reset(itemCatalog);
    }

    @Test
    public void load_whenInsertedInvalidCount_thenRepeatQuestion() throws Exception {
        PrintStream printStream = mock(PrintStream.class);
        StringBuilder inputs = new StringBuilder()
                .append("-1").append(lineSeparator())
                .append("1.2").append(lineSeparator())
                .append("0").append(lineSeparator());
        when(itemCatalog.getItems()).thenReturn(new ArrayList<Item>() {{
            add(Item.of(ITEM_NAME, ITEM_PRICE));
        }});

        Basket actual = underTest.load(itemCatalog, inputStream(inputs), printStream);

        assertThat(actual.getItems()).containsOnly(BasketItem.of(ITEM_NAME, ITEM_PRICE, 0));
        verify(itemCatalog).getItems();
        verify(printStream).println("What is the number of apple(s)?");
        verify(printStream, times(2)).println("Invalid number. What is the number of apple(s)?");
    }

    private InputStream inputStream(StringBuilder stringToConvert) {
        return new ByteArrayInputStream(stringToConvert.toString().getBytes(StandardCharsets.UTF_8));
    }
}