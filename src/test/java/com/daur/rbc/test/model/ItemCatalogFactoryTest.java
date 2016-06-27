package com.daur.rbc.test.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

public class ItemCatalogFactoryTest {

    private final ItemCatalogFactory underTest = new ItemCatalogFactory();

    private File catalogFile;

    @Before
    public void setUp() throws Exception {
        catalogFile = getItemCatalogFilePath();
    }

    @After
    public void tearDown() throws Exception {
        catalogFile.delete();
    }

    private File getItemCatalogFilePath() throws IOException {
        return File.createTempFile("item_catalog_" + System.currentTimeMillis(), ".properties");
    }

    @Test
    public void create_whenArgsIsNull_thenThrowIllegalArgumentException() {
        try {
            underTest.create(null);

            fail("Exception expected");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Item catalog path not specified");
        }
    }

    @Test
    public void create_whenPathNoSpecified_thenThrowIllegalArgumentException() {
        try {
            underTest.create(new String[]{});

            fail("Exception expected");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Item catalog path not specified");
        }
    }

    @Test
    public void create_whenFileNotFound_thenThrowIllegalArgumentException() {
        try {
            underTest.create(new String[]{"test.properties"});

            fail("Exception expected");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Provided item catalog path is invalid");
        }
    }

    @Test
    public void create_whenReadPropertiesAreEmpty_thenThrowIllegalArgumentException() {
        try {
            underTest.create(new String[]{catalogFile.getAbsolutePath()});

            fail("Exception expected");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("File empty or not in '.properties' format");
        }
    }

    @Test
    public void create_whenValid_thenReturnCreatedItemCatalog() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(catalogFile)) {
            writer.println("banana=1.2");
            writer.println("apple=1.0");
        }

        ItemCatalog actual = underTest.create(new String[]{catalogFile.getAbsolutePath()});

        //assertThat(actual).includes(entry("banana", "1.2"), entry("apple", "1.0"));
    }
}
