package com.restapi.product.service;

import com.restapi.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Page<Product> getAllProducts(Pageable pageable);
    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> searchProductsByName(String name);
    boolean checkStockAvailability(Long productId, Integer requiredQuantity);
}