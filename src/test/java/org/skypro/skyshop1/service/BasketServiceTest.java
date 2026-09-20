package org.skypro.skyshop1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop1.exception.NoSuchProductException;
import org.skypro.skyshop1.model.basket.BasketItem;
import org.skypro.skyshop1.model.basket.ProductBasket;
import org.skypro.skyshop1.model.basket.UserBasket;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.service.BasketService;
import org.skypro.skyshop1.service.StorageService;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {
    private ProductBasket productBasket;
    private StorageService storageService;
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        // Создаем моки
        productBasket = mock(ProductBasket.class);
        storageService = mock(StorageService.class);
        // Передаем моки в сервис
        basketService = new BasketService(productBasket, storageService);
    }

    @Test
    void addNonExistingProductThrowsException() {
        // Мокаем storageService: товар не найден
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());
        // Проверяем, что выбрасывается исключение
        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
    }

    @Test
    void addExistingProductCallsAddProduct() {
        // Мокаем существующий товар
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        // Добавляем товар в корзину
        basketService.addProduct(id);
        // Проверяем, что у мока был вызван addProduct
        verify(productBasket, times(1)).addProduct(id);
    }

    @Test
    void getUserBasketReturnsEmptyWhenBasketIsEmpty() {
        // Корзина пуста
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        // Проверяем, что корзина действительно пуста
        assertTrue(userBasket.getItems().isEmpty());
    }

    @Test
    void getUserBasketReturnsBasketWithItems() {
        // В корзине есть товар
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(productBasket.getProducts()).thenReturn(Map.of(id, 2));
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        UserBasket userBasket = basketService.getUserBasket();
        // Проверяем, что корзина содержит 1 товар
        assertEquals(1, userBasket.getItems().size());
        BasketItem item = userBasket.getItems().get(0);
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getCount());
    }
}
