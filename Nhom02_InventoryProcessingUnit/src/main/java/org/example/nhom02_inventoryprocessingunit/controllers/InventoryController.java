package org.example.controllers;

import org.example.services.InventoryService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*")
public class InventoryController {
    private final StringRedisTemplate redisTemplate;

    public InventoryController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 1. Xem tồn kho
    @GetMapping("/{productId}")
    public String getStock(@PathVariable String productId) {
        String stock = redisTemplate.opsForValue().get("inventory:" + productId);
        return stock != null ? stock : "0";
    }

    // 2. API trừ tồn kho (Dành cho PU3 gọi sang khi checkout)
    @PostMapping("/deduct")
    public ResponseEntity<String> deductStock(@RequestParam String productId, @RequestParam int amount) {
        String stockKey = "inventory:" + productId;

        // DECRBY trực tiếp trên Redis đảm bảo Single-Thread, không bị Overselling
        Long currentStock = redisTemplate.opsForValue().decrement(stockKey, amount);

        if (currentStock != null && currentStock >= 0) {
            return ResponseEntity.ok("TRU_THANH_CONG");
        } else {
            // Hoàn lại kho nếu số lượng âm (mua lố)
            redisTemplate.opsForValue().increment(stockKey, amount);
            return ResponseEntity.badRequest().body("HET_HANG");
        }
    }

    //    private final InventoryService inventoryService;
//
//    public InventoryController(InventoryService inventoryService) {
//        this.inventoryService = inventoryService;
//    }
//
//    @GetMapping("/{productId")
//    public ResponseEntity<String> getStock (@PathVariable String productId) {
//        return ResponseEntity.ok(inventoryService.getStock(productId));
//    }
//
//    @PostMapping("/{deduct}")
//    public ResponseEntity<String> deductStock (@RequestParam String productId, @RequestParam int amount) {
//        boolean success = inventoryService.deductStock(productId, amount);
//
//        if (success) {
//            return ResponseEntity.ok("TRU_THANH_CONG");
//        } else {
//            return ResponseEntity.badRequest().body("HET_HANG");
//        }
//    }
}