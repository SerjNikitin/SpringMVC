package com.example.springmvc.mvcLayer.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.component.ShoppingCart;
import com.example.springmvc.mvcLayer.domain.Address;
import com.example.springmvc.mvcLayer.domain.Order;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.domain.security.Role;
import com.example.springmvc.mvcLayer.domain.security.User;
import com.example.springmvc.mvcLayer.repository.AddressRepository;
import com.example.springmvc.mvcLayer.repository.OrderRepository;
import com.example.springmvc.mvcLayer.service.UserService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class, ShoppingCart.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @Autowired
    private ShoppingCart shoppingCart;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private UserService userService;

    @Test
    void testSave() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        when(this.userService.findByUsername((String) any())).thenReturn(user);

        User user1 = new User();
        user1.setPassword("iloveyou");
        user1.setEmail("jane.doe@example.org");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setRoles(new ArrayList<Role>());

        Address address = new Address();
        address.setId(1);
        address.setCity("Oxford");
        address.setApartment(1);
        address.setHouse(1);
        address.setStreet("Street");

        Order order = new Order();
        order.setDate(LocalDate.ofEpochDay(1L));
        order.setStatus("Status");
        order.setUserId(user1);
        order.setId(1);
        order.setAddress(address);
        order.setCart(new ArrayList<ProductDto>());
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        Address address1 = new Address();
        address1.setId(1);
        address1.setCity("Oxford");
        address1.setApartment(1);
        address1.setHouse(1);
        address1.setStreet("Street");
        when(this.addressRepository.save((Address) any())).thenReturn(address1);

        Address address2 = new Address();
        address2.setId(1);
        address2.setCity("Oxford");
        address2.setApartment(1);
        address2.setHouse(1);
        address2.setStreet("Street");
        this.orderServiceImpl.save(this.shoppingCart, address2, "Name");
        verify(this.userService).findByUsername((String) any());
        verify(this.orderRepository).save((Order) any());
        verify(this.addressRepository).save((Address) any());
    }

    @Test
    void testGetAllOrders() {
        ArrayList<Order> orderList = new ArrayList<Order>();
        when(this.orderRepository.findOrdersByUserId_Username((String) any())).thenReturn(orderList);
        List<Order> actualAllOrders = this.orderServiceImpl.getAllOrders("Name");
        assertSame(orderList, actualAllOrders);
        assertTrue(actualAllOrders.isEmpty());
        verify(this.orderRepository).findOrdersByUserId_Username((String) any());
    }
}

