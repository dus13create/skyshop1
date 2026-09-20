package org.skypro.skyshop1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.model.search.Searchable;
import org.skypro.skyshop1.service.SearchService;
import org.skypro.skyshop1.service.StorageService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchServiceTest {
    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        // Создаем мок для StorageService
        storageService = mock(StorageService.class);
        // Передаем мок в сервис поиска
        searchService = new SearchService(storageService);
    }

    @Test
    void searchWhenStorageEmpty() {
        // Хранилище пустое
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());
        List result = searchService.search("яблоко");
        // Ожидаем, что найдено ничего не будет
        assertTrue(result.isEmpty());
    }

    @Test
    void searchNoMatches() {
        // В хранилище есть объект, но он не подходит
        Product product = mock(Product.class);
        when(product.toString()).thenReturn("Банан");
        when(storageService.getAllSearchables()).thenReturn(List.of(product));
        List result = searchService.search("яблоко");
        // Совпадений нет
        assertTrue(result.isEmpty());
    }

    @Test
    void searchWithMatch() {
        // В хранилище есть подходящий объект
        Product product = mock(Product.class);
        when(product.toString()).thenReturn("Яблоко");
        when(storageService.getAllSearchables()).thenReturn(List.of(product));
        List result = searchService.search("Яблоко");
        // Найден ровно один объект
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }
}
