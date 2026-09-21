package org.skypro.skyshop1.model.product;
import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 100; // Константа для фиксированной цены

    public FixPriceProduct(UUID id,String name) {
        super(id,name);
    }

    // Возвращает фиксированную цену
    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    // Этот товар считается специальным
    @Override
    public boolean isSpecial() {
        return true;
    }

    // Переопределенный вывод для фиксированной цены
    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }
    @Override
    public boolean matches(String term) {
        return getName().toLowerCase().contains(term.toLowerCase());
    }
}
