package org.skypro.skyshop1.service;

import org.skypro.skyshop1.model.search.SearchResult;
import org.skypro.skyshop1.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection search(String pattern) {
        return storageService.getAllSearchables().stream()
                .filter(s -> s.getName().matches(pattern))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
    }

