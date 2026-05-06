package org.example.services;

public interface InventoryService {
    String getStock (String productId);
    boolean deductStock (String productId, int amount);
}
