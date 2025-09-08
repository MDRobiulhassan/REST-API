package com.restapi.product.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
    
    public InsufficientStockException(Long productId, int requestedQuantity, int availableQuantity) {
        super(String.format("Insufficient stock for product ID %d. Requested: %d, Available: %d", 
              productId, requestedQuantity, availableQuantity));
    }
}
