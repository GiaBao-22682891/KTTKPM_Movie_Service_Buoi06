package org.example.services.impl;

import org.example.services.InventoryService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "inventory:";

    public InventoryServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getStock(String productId) {
        String stock = redisTemplate.opsForValue().get(KEY_PREFIX + productId);
        return stock != null? stock : "0";
    }

    @Override
    public boolean deductStock(String productId, int amount) {
        String stockKey = KEY_PREFIX + productId;

        Long currentStock = redisTemplate.opsForValue().decrement(stockKey);

        if (currentStock != null && currentStock >= 0) {
            return true;
        } else {
            redisTemplate.opsForValue().increment(stockKey);
            return false;
        }
    }
}
