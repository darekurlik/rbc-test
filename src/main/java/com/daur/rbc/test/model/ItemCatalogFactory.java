package com.daur.rbc.test.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ItemCatalogFactory {

    public ItemCatalog create(String[] args) {
        validate(args);
        return readCatalogFromFile(args[0]);
    }

    private void validate(String[] args) {
        validateArgs(args);
        validateFilePath(args[0]);
    }

    private void validateArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Item catalog path not specified");
        }
    }

    private void validateFilePath(String filePath) {
        if (!new File(filePath).exists()) {
            throw new IllegalArgumentException("Provided item catalog path is invalid");
        }
    }

    private ItemCatalog readCatalogFromFile(String arg) {
        Properties fileContent = readFile(arg);
        validateFileContent(fileContent);
        return ItemCatalog.ofValidProperties(fileContent);
    }

    private Properties readFile(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Properties result = new Properties();
            result.load(inputStream);
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("The Item Catalog is in invalid format");
        }
    }

    private void validateFileContent(Properties fileContent) {
        if (fileContent.isEmpty()) {
            throw new IllegalArgumentException("File empty or not in '.properties' format");
        }
    }

}
