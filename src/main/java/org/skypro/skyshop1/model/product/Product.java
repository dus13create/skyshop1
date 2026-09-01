package org.skypro.skyshop1.model.product;
import org.skypro.skyshop1.model.search.Searchable;

import java.util.Collection;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String name;
    // Конструктор с проверкой названия
    public Product(UUID id,String name) {
        // Проверка: название не должно быть null или "пустым" (включая пробелы)
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или состоять только из пробелов");
        }
        this.name = name;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }
    // Сравниваем продукты только по имени
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // если ссылки совпадают
        if (o == null || getClass() != o.getClass()) return false; // если объект другого типа
        Product product = (Product) o; // приводим к Product
        return name.equals(product.name); // имена должны совпадать
    }

    // Хэш-код также только по имени

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    // Геттер имени
    public String getName() {
        return name;
    }
    // Абстрактный метод для получения цены товара
    public abstract int getPrice();
    // Метод для проверки, является ли товар специальным
    public abstract boolean isSpecial();
    // Стандартный формат вывода информации о товаре
    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return name;
    }
    @Override
    public String getContentType() {
        return "PRODUCT";
    }
    @Override
    public boolean matches(String term) {
        return getName().toLowerCase().contains(term.toLowerCase());
    }
}



