package org.skypro.skyshop1.service;

import org.skypro.skyshop1.model.basket.BasketItem;
import org.skypro.skyshop1.model.basket.ProductBasket;
import org.skypro.skyshop1.model.basket.UserBasket;
import org.skypro.skyshop1.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.skypro.skyshop1.exception.NoSuchProductException;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional productOpt = storageService.getProductById(id);
        // Если товар не найден, выбрасываем свое исключение
        if (!productOpt.isPresent()) {
            throw new NoSuchProductException("Product not found: " + id);
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List basketItems = productBasket.getProducts().entrySet().stream()
                .map(entry -> new BasketItem(
                        storageService.getProductById(entry.getKey()).orElseThrow(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
        return new UserBasket(basketItems);
    }
}
