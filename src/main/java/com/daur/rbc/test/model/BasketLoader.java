package com.daur.rbc.test.model;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.daur.rbc.test.model.BasketItem.of;

public class BasketLoader {

    public Basket load(ItemCatalog itemCatalog, InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        List<BasketItem> basketItems = itemCatalog.getItems()
                .stream()
                .map(each -> of(each, readCount(each, scanner, out)))
                .collect(Collectors.toList());
        return new Basket(basketItems);
    }

    private int readCount(Item item, Scanner scanner, PrintStream out) {
        out.println("What is the number of " + item.getName() + "(s)?");
        while (scanner.hasNext()) {
            int count = readCount(scanner);
            if (count >= 0) {
                return count;
            } else {
                out.println("Invalid number. What is the number of " + item.getName() + "(s)?");
            }
        }
        throw new RuntimeException("Unexpected end of input");
    }

    private int readCount(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (RuntimeException e) {
            scanner.nextLine();
            return -1;
        }
    }
}
