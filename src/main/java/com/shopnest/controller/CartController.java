package com.shopnest.controller;

import com.shopnest.model.CartItem;
import com.shopnest.model.User;
import com.shopnest.repository.UserRepository;
import com.shopnest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String viewCart(Model model, Authentication authentication) {
        User user = getCurrentUser(authentication);
        List<CartItem> cartItems = cartService.getCartItems(user);
        Double cartTotal = cartService.getCartTotal(user);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartTotal);
        model.addAttribute("cartCount", cartItems.size());
        model.addAttribute("currentUser", user);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            Authentication authentication,
                            RedirectAttributes redirectAttributes) {
        User user = getCurrentUser(authentication);
        cartService.addToCart(user, productId, quantity);
        redirectAttributes.addFlashAttribute("successMessage", "Product added to cart!");
        return "redirect:/cart";
    }

    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable Long id,
                                 @RequestParam int quantity,
                                 RedirectAttributes redirectAttributes) {
        cartService.updateQuantity(id, quantity);
        redirectAttributes.addFlashAttribute("successMessage", "Cart updated!");
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        cartService.removeFromCart(id);
        redirectAttributes.addFlashAttribute("successMessage", "Item removed from cart!");
        return "redirect:/cart";
    }

    private User getCurrentUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
