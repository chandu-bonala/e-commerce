package com.shopnest.controller;

import com.shopnest.model.*;
import com.shopnest.repository.UserRepository;
import com.shopnest.service.CartService;
import com.shopnest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/checkout")
    public String checkout(Model model, Authentication authentication) {
        User user = getCurrentUser(authentication);
        List<CartItem> cartItems = cartService.getCartItems(user);
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartService.getCartTotal(user));
        model.addAttribute("cartCount", cartItems.size());
        model.addAttribute("currentUser", user);
        return "checkout";
    }

    @PostMapping("/place")
    public String placeOrder(@RequestParam String address,
                             @RequestParam String city,
                             @RequestParam String state,
                             @RequestParam String zipCode,
                             @RequestParam String paymentMethod,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        User user = getCurrentUser(authentication);
        try {
            Order order = orderService.placeOrder(user, address, city, state, zipCode, paymentMethod);
            redirectAttributes.addFlashAttribute("successMessage", "Order placed successfully!");
            return "redirect:/orders/confirmation/" + order.getOrderNumber();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/orders/checkout";
        }
    }

    @GetMapping("/confirmation/{orderNumber}")
    public String orderConfirmation(@PathVariable String orderNumber, Model model, Authentication authentication) {
        Order order = orderService.getOrderByNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        User user = getCurrentUser(authentication);

        model.addAttribute("order", order);
        model.addAttribute("currentUser", user);
        model.addAttribute("cartCount", cartService.getCartCount(user));
        return "order-confirmation";
    }

    @GetMapping("/my-orders")
    public String myOrders(Model model, Authentication authentication) {
        User user = getCurrentUser(authentication);
        model.addAttribute("orders", orderService.getUserOrders(user));
        model.addAttribute("currentUser", user);
        model.addAttribute("cartCount", cartService.getCartCount(user));
        return "my-orders";
    }

    private User getCurrentUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
