package org.skypro.skyshop1.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope

public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
    public void addProduct(UUID id) {
        products.put(id, products.getOrDefault(id, 0) + 1);
    }
}
