package com.shopnest.controller;

import com.shopnest.model.Product;
import com.shopnest.model.User;
import com.shopnest.repository.UserRepository;
import com.shopnest.service.CartService;
import com.shopnest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listProducts(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "12") int size,
                               @RequestParam(defaultValue = "newest") String sort,
                               @RequestParam(required = false) Long category,
                               @RequestParam(required = false) String search,
                               Model model,
                               Authentication authentication) {

        Page<Product> products;
        if (search != null && !search.trim().isEmpty()) {
            products = productService.searchProducts(search, page, size);
            model.addAttribute("searchKeyword", search);
        } else if (category != null) {
            products = productService.getProductsByCategory(category, page, size, sort);
            model.addAttribute("selectedCategory", category);
        } else {
            products = productService.getProducts(page, size, sort);
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentPage", page);
        addUserAttributes(model, authentication);
        return "products";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model, Authentication authentication) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        model.addAttribute("product", product);
        model.addAttribute("relatedProducts", productService.getRelatedProducts(product, 4));
        addUserAttributes(model, authentication);
        return "product-detail";
    }

    private void addUserAttributes(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(authentication.getName()).orElse(null);
            model.addAttribute("currentUser", user);
            if (user != null) {
                model.addAttribute("cartCount", cartService.getCartCount(user));
            }
        }
    }
}
