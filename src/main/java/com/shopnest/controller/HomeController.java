package com.shopnest.controller;

import com.shopnest.model.User;
import com.shopnest.repository.UserRepository;
import com.shopnest.service.CartService;
import com.shopnest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("featuredProducts", productService.getFeaturedProducts());
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("deals", productService.getDeals(4));
        model.addAttribute("latestProducts", productService.getLatestProducts());
        addUserAttributes(model, authentication);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model, Authentication authentication) {
        addUserAttributes(model, authentication);
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model, Authentication authentication) {
        addUserAttributes(model, authentication);
        return "contact";
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
