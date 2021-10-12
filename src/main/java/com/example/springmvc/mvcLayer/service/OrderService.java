package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.component.ShoppingCart;
import com.example.springmvc.mvcLayer.domain.Address;
import com.example.springmvc.mvcLayer.domain.Order;

import java.util.List;

public interface OrderService {

    void save(ShoppingCart shoppingCart, Address address, String name );
    List<Order> getAllOrders(String name);
}
