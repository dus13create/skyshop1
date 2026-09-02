package org.skypro.skyshop1.model.search;
import java.util.UUID;

public interface Searchable {
    // Поисковый термин
    String getSearchTerm();
    // Тип контента
    String getContentType();
    // Имя объекта
    String getName();
    // Метод для поиска
    boolean matches(String term);
    // Строковое представление
    default String getStringRepresentation() {

        return getName() + " - " + getContentType();
    }
    UUID getId();
}