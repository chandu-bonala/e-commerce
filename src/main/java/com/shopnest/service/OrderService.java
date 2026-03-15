package com.shopnest.service;

import com.shopnest.model.*;
import com.shopnest.repository.OrderRepository;
import com.shopnest.repository.CartItemRepository;
import com.shopnest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order placeOrder(User user, String address, String city, String state, String zip, String paymentMethod) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setOrderNumber("SN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setUser(user);
        order.setShippingAddress(address);
        order.setShippingCity(city);
        order.setShippingState(state);
        order.setShippingZip(zip);
        order.setPaymentMethod(paymentMethod);
        order.setStatus(Order.OrderStatus.CONFIRMED);

        double total = 0;
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            // Reduce stock
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem(product, cartItem.getQuantity(), product.getPrice());
            order.addOrderItem(orderItem);
            total += product.getPrice() * cartItem.getQuantity();
        }

        order.setTotalPrice(total);
        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cartItemRepository.deleteByUser(user);

        return savedOrder;
    }

    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Optional<Order> getOrderByNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Order> getRecentOrders(int limit) {
        return orderRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, limit));
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public long countOrders() {
        return orderRepository.count();
    }

    public long countPendingOrders() {
        return orderRepository.countByStatus(Order.OrderStatus.PENDING) +
               orderRepository.countByStatus(Order.OrderStatus.CONFIRMED);
    }

    public Double getTotalRevenue() {
        Double revenue = orderRepository.getTotalRevenue();
        return revenue != null ? revenue : 0.0;
    }

    public Double getMonthlyRevenue() {
        Double revenue = orderRepository.getMonthlyRevenue();
        return revenue != null ? revenue : 0.0;
    }
}
