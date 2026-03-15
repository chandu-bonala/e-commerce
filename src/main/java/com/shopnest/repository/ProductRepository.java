package com.shopnest.repository;

import com.shopnest.model.Product;
import com.shopnest.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByFeaturedTrueAndActiveTrue();

    List<Product> findByActiveTrueOrderByCreatedAtDesc();

    Page<Product> findByActiveTrue(Pageable pageable);

    Page<Product> findByCategoryAndActiveTrue(Category category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Product> searchProducts(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.originalPrice IS NOT NULL AND p.originalPrice > p.price ORDER BY (p.originalPrice - p.price) DESC")
    List<Product> findDeals(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.category = :category AND p.id != :productId")
    List<Product> findRelatedProducts(@Param("category") Category category, @Param("productId") Long productId, Pageable pageable);

    @Query("SELECT MIN(p.price) FROM Product p WHERE p.active = true")
    Double findMinPrice();

    @Query("SELECT MAX(p.price) FROM Product p WHERE p.active = true")
    Double findMaxPrice();

    long countByActiveTrue();
}
