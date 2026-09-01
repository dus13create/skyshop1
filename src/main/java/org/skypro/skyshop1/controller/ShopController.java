package org.skypro.skyshop1.controller;
import org.skypro.skyshop1.model.basket.UserBasket;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.model.article.Article;
import org.skypro.skyshop1.service.BasketService;
import org.skypro.skyshop1.service.SearchService;
import org.skypro.skyshop1.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection getAllArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public Collection search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    // ... basket endpoints
}

