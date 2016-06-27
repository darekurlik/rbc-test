package com.daur.rbc.test.integration;

import com.daur.rbc.test.main.RBCTestMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;

public class RBCIntegrationTest {

    private File catalogFile;

    @Before
    public void setUp() throws Exception {
        catalogFile = getItemCatalogFilePath();
    }

    @After
    public void tearDown() throws Exception {
        catalogFile.delete();
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void whenValidInput_thenReturnBasketTotalCost() throws Exception {
        populateCatalog(1.2, 1.5, 1.0, 0.5, 2.1);
        writeInput(4, 0, 2, 1, 3);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        registerOutput(outputStream);

        RBCTestMain.main(new String[]{catalogFile.getAbsolutePath()});

        assertThat(outputStream.toString())
                .contains("What is the number of banana(s)?")
                .contains("What is the number of orange(s)?")
                .contains("What is the number of apple(s)?")
                .contains("What is the number of lemon(s)?")
                .contains("What is the number of peach(s)?")
                .contains("13.6");
    }

    private void registerOutput(ByteArrayOutputStream outputStream) {
        System.setOut(new PrintStream(outputStream));
    }

    private void populateCatalog(double bananaPrice,
                                 double orangePrice,
                                 double applePrice,
                                 double lemonPrice,
                                 double peachPrice) throws IOException {
        Properties properties = new Properties();
        properties.put("banana", String.valueOf(bananaPrice));
        properties.put("orange", String.valueOf(orangePrice));
        properties.put("apple", String.valueOf(applePrice));
        properties.put("lemon", String.valueOf(lemonPrice));
        properties.put("peach", String.valueOf(peachPrice));

        try (OutputStream out = new FileOutputStream(catalogFile)) {
            properties.store(out, null);
        }
    }

    private void writeInput(int bananaCount,
                            int orangeCount,
                            int appleCount,
                            int lemonCount,
                            int peachCount) {
        String input = Arrays.stream(new int[]{lemonCount, peachCount, orangeCount, bananaCount, appleCount})
                .mapToObj(each -> each + System.lineSeparator())
                .collect(Collectors.joining(""));

        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private File getItemCatalogFilePath() throws IOException {
        return File.createTempFile("item_catalog_" + System.currentTimeMillis(), ".properties");
    }
}
