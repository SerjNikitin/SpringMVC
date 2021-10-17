package com.example.springmvc.mvcLayer.service.impl;

import com.example.springmvc.mvcLayer.component.ShoppingCart;
import com.example.springmvc.mvcLayer.domain.Address;
import com.example.springmvc.mvcLayer.domain.Order;
import com.example.springmvc.mvcLayer.domain.cart.CartItem;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.domain.security.User;
import com.example.springmvc.mvcLayer.repository.AddressRepository;
import com.example.springmvc.mvcLayer.repository.OrderRepository;
import com.example.springmvc.mvcLayer.service.OrderService;
import com.example.springmvc.mvcLayer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public void save(ShoppingCart shoppingCart, Address address, String name) {
        Address save = addressRepository.save(address);
        User user = userService.findByUsername(name);
        Order order = new Order();
        List<ProductDto> cart = new ArrayList<>();
        Map<Integer, CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem value : cartItems.values()) {
            ProductDto product = value.getProduct();
            cart.add(product);
            shoppingCart.deleteCartItem(product.getId());
        }
        order.setStatus("new");
        order.setAddress(save);
        order.setUserId(user);
        order.setCart(cart);
        order.setDate(LocalDate.now());
        orderRepository.save(order);
        shoppingCart.getCartItems().clear();
    }

    @Override
    public List<Order> getAllOrders(String name) {
        return orderRepository.findOrdersByUserId_Username(name);
    }
}
