package org.skypro.skyshop1.model.product;
import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price; // Цена товара

    public SimpleProduct(UUID id,String name, int price) {
        super(id,name);
        // Проверка: цена должна быть строго больше 0
        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть больше 0");
        }
        this.price = price;
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public boolean isSpecial() {
        return false;
    }
    @Override
    public boolean matches(String term) {
        return getName().toLowerCase().contains(term.toLowerCase());
    }
}
