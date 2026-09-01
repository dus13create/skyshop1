package org.skypro.skyshop1.model.article;
import java.util.UUID;
import org.skypro.skyshop1.model.search.Searchable;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Article implements Searchable {
    private final String title;
    private final String text;
    private final UUID id;

    public Article(UUID id,String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // если это один и тот же объект
        if (o == null || getClass() != o.getClass()) return false; // если объект другого типа
        Article article = (Article) o; // приводим к Article
        return title.equals(article.title); // сравниваем только по названию (заголовку)
    }

    @Override
    public UUID getId() {
        return id;
    }
    @Override
    public int hashCode() {

        return title.hashCode(); // хэш-код только по названию
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title + " " + text;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }
    @Override
    public boolean matches(String term) {
        return title.toLowerCase().contains(term.toLowerCase()) ||
                text.toLowerCase().contains(term.toLowerCase());
    }
}

