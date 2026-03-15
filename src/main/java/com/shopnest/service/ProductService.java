package com.shopnest.service;

import com.shopnest.model.Product;
import com.shopnest.model.Category;
import com.shopnest.repository.ProductRepository;
import com.shopnest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getFeaturedProducts() {
        return productRepository.findByFeaturedTrueAndActiveTrue();
    }

    public List<Product> getLatestProducts() {
        return productRepository.findByActiveTrueOrderByCreatedAtDesc();
    }

    private Sort getSortOrder(String sortBy) {
        if (sortBy == null) return Sort.by("createdAt").descending();
        switch (sortBy) {
            case "price_asc": return Sort.by("price").ascending();
            case "price_desc": return Sort.by("price").descending();
            case "name": return Sort.by("name").ascending();
            case "newest": return Sort.by("createdAt").descending();
            case "rating": return Sort.by("rating").descending();
            default: return Sort.by("createdAt").descending();
        }
    }

    public Page<Product> getProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, getSortOrder(sortBy));
        return productRepository.findByActiveTrue(pageable);
    }

    public Page<Product> getProductsByCategory(Long categoryId, int page, int size, String sortBy) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) return Page.empty();
        Pageable pageable = PageRequest.of(page, size, getSortOrder(sortBy));
        return productRepository.findByCategoryAndActiveTrue(category, pageable);
    }

    public Page<Product> searchProducts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return productRepository.searchProducts(keyword, pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getDeals(int limit) {
        return productRepository.findDeals(PageRequest.of(0, limit));
    }

    public List<Product> getRelatedProducts(Product product, int limit) {
        return productRepository.findRelatedProducts(product.getCategory(), product.getId(), PageRequest.of(0, limit));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public long countProducts() {
        return productRepository.countByActiveTrue();
    }

    public Double getMinPrice() {
        Double min = productRepository.findMinPrice();
        return min != null ? min : 0.0;
    }

    public Double getMaxPrice() {
        Double max = productRepository.findMaxPrice();
        return max != null ? max : 10000.0;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
