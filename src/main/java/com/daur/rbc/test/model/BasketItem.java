package com.daur.rbc.test.model;

public class BasketItem extends Item {
    private final int count;

    private BasketItem(Item item, int count) {
        super(item.getName(), item.getPrice());
        this.count = count;
    }

    public static BasketItem of(String name, double price, int count) {
        return of(Item.of(name, price), count);
    }

    public static BasketItem of(Item item, int count) {
        return new BasketItem(item, count);
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "count=" + count +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BasketItem that = (BasketItem) o;

        return count == that.count;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + count;
        return result;
    }
}
