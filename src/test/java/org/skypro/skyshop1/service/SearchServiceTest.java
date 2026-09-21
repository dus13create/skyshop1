package org.skypro.skyshop1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop1.model.product.Product;
import org.skypro.skyshop1.model.search.SearchResult;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchServiceTest {
    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        searchService = new SearchService(storageService);
    }

    @Test
    void searchWhenStorageEmpty() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());
        Collection result = searchService.search("яблоко");
        assertTrue(result.isEmpty());
    }

    @Test
    void searchNoMatches() {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn("Банан");
        when(storageService.getAllSearchables()).thenReturn(List.of(product));
        Collection result = searchService.search("яблоко");
        assertTrue(result.isEmpty());
    }

    @Test
    void searchWithMatch() {
        Product product = mock(Product.class);
        UUID id = UUID.randomUUID();
        when(product.getName()).thenReturn("Яблоко");
        when(product.getId()).thenReturn(id);
        when(storageService.getAllSearchables()).thenReturn(List.of(product));
        Collection<SearchResult> result = searchService.search("Яблоко"); // <--- исправлено!
        assertEquals(1, result.size());
        SearchResult searchResult = result.iterator().next();
        assertEquals("Яблоко", searchResult.getName());
        assertEquals(id.toString(), searchResult.getId());
    }
}