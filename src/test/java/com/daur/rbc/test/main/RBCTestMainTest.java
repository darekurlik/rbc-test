package com.daur.rbc.test.main;

import com.daur.rbc.test.model.Basket;
import com.daur.rbc.test.model.BasketLoader;
import com.daur.rbc.test.model.ItemCatalog;
import com.daur.rbc.test.model.ItemCatalogFactory;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.portable.InputStream;

import java.io.PrintStream;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RBCTestMainTest {

    private static final String[] ARGS = new String[]{"test.txt"};

    private final ItemCatalogFactory itemCatalogFactory = mock(ItemCatalogFactory.class);
    private final BasketLoader basketLoader = mock(BasketLoader.class);
    private final ItemCatalog itemCatalog = mock(ItemCatalog.class);

    private final RBCTestMain underTest = new RBCTestMain(itemCatalogFactory, basketLoader);

    @Before
    public void setUp() throws Exception {
        reset(itemCatalogFactory, basketLoader, itemCatalog);
    }

    @Test
    public void performMainLogic_whenException_thenReturnErrorMassage() {
        String exceptionMessage = "expected message";
        when(itemCatalogFactory.create(ARGS)).thenThrow(new RuntimeException(exceptionMessage));

        String actual = underTest.performMainLogic(ARGS);

        assertThat(actual).isEqualTo("ERROR: " + exceptionMessage);
        verify(itemCatalogFactory).create(ARGS);
        verifyNoMoreInteractions(itemCatalogFactory, basketLoader);
    }

    @Test
    public void performMainLogic_whenNoException_thenReturnResult() {
        Double expectedResult = 1.2;
        InputStream inputStream = mock(InputStream.class);
        PrintStream printStream = mock(PrintStream.class);
        Basket basket = mock(Basket.class);
        System.setIn(inputStream);
        System.setOut(printStream);
        when(itemCatalogFactory.create(ARGS)).thenReturn(itemCatalog);
        when(basketLoader.load(itemCatalog, inputStream, printStream)).thenReturn(basket);
        when(basket.calculateCost()).thenReturn(expectedResult);

        String actual = underTest.performMainLogic(ARGS);

        assertThat(actual).isEqualTo(expectedResult.toString());
        verify(itemCatalogFactory).create(ARGS);
        verify(basketLoader).load(itemCatalog, inputStream, printStream);
        verify(basket).calculateCost();
        verifyNoMoreInteractions(itemCatalogFactory, basketLoader, basket);
    }
}
