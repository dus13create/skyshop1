package org.skypro.skyshop1.model.search;
import org.skypro.skyshop1.model.search.Searchable;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        items.add(item);
    }


    public Set<Searchable> search(String term) {
        // Используем Stream API и специальный коллектор для TreeSet с нужным компаратором
        return items.stream()
                .filter(item -> item.matches(term))
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>((a, b) -> {
                            int cmp = Integer.compare(b.getName().length(), a.getName().length());
                            if (cmp == 0) {
                                return a.getName().compareTo(b.getName());
                            }
                            return cmp;
                        })
                ));
    }
}