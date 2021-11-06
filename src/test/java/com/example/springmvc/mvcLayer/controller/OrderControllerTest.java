package com.example.springmvc.mvcLayer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.example.springmvc.mvcLayer.component.ShoppingCart;
import com.example.springmvc.mvcLayer.repository.AddressRepository;
import com.example.springmvc.mvcLayer.repository.OrderRepository;
import com.example.springmvc.mvcLayer.repository.UserRepository;
import com.example.springmvc.mvcLayer.service.RoleService;
import com.example.springmvc.mvcLayer.service.impl.OrderServiceImpl;
import com.example.springmvc.mvcLayer.service.impl.security.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.ui.ConcurrentModel;

class OrderControllerTest {
    @Test
    void testCreateOrderView() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        RoleService roleService = mock(RoleService.class);
        OrderController orderController = new OrderController(new OrderServiceImpl(orderRepository,
                new UserServiceImpl(userRepository, roleService, new Argon2PasswordEncoder()), mock(AddressRepository.class)));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("order/newOrder", orderController.createOrderView(model, new ShoppingCart()));
    }
}

