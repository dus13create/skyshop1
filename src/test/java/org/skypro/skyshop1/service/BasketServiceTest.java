package org.skypro.skyshop1.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    void addNonExistingProductThrowsException() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
    }

    @Test
    void addExistingProductCallsAddProduct() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        basketService.addProduct(id);
        verify(productBasket, times(1)).addProduct(id);
    }

    @Test
    void getUserBasketReturnsEmptyWhenBasketIsEmpty() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertTrue(userBasket.getItems().isEmpty());
        assertEquals(0, userBasket.getTotal()); // Проверка total
    }

    @Test
    void getUserBasketReturnsBasketWithItems() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(productBasket.getProducts()).thenReturn(Map.of(id, 2));
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        when(product.getPrice()).thenReturn(100); // Мокаем цену
        UserBasket userBasket = basketService.getUserBasket();
        assertEquals(1, userBasket.getItems().size());
        BasketItem item = userBasket.getItems().get(0);
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
        assertEquals(200, userBasket.getTotal()); // Проверка total
    }
}
