package org.skypro.skyshop1.service;

import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.model.article.Article;
import org.skypro.skyshop1.model.product.SimpleProduct;
import org.skypro.skyshop1.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        fillTestData();
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public Collection<Article> getAllArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> all = new ArrayList<>();
        all.addAll(getAllProducts());
        all.addAll(getAllArticles());
        return all;
    }

    private void fillTestData() {
        Product apple = new SimpleProduct(UUID.randomUUID(), "Яблоко", 50);
        Product banana = new SimpleProduct(UUID.randomUUID(), "Банан", 30);
        Product chocolate = new SimpleProduct(UUID.randomUUID(), "Шоколад", 60);
        productMap.put(apple.getId(), apple);
        productMap.put(banana.getId(), banana);
        productMap.put(chocolate.getId(), chocolate);

        Article article1 = new Article(UUID.randomUUID(), "Польза яблок", "Яблоки богаты витаминами и железом.");
        Article article2 = new Article(UUID.randomUUID(), "Шоколад и настроение", "Шоколад улучшает настроение.");
        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
    }
}