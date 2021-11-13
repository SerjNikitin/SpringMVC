package com.example.springmvc.mvcLayer.controller;

import com.example.springmvc.mvcLayer.component.ShoppingCart;
import com.example.springmvc.mvcLayer.domain.Address;
import com.example.springmvc.mvcLayer.domain.Order;
import com.example.springmvc.mvcLayer.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.springmvc.mvcLayer.domain.constans.ConstanceName.*;

@Controller
@AllArgsConstructor
@SessionAttributes("shoppingCart")
@RequestMapping(ORDER)
public class OrderController {

    private final OrderService orderService;

    @GetMapping(LIST)
    public String getAllOrders(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = orderService.getAllOrders(name);
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @GetMapping(FORM)
    public String createOrderView(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute("address", new Address());
        return "order/newOrder";
    }

    @PostMapping(FORM)
    public String createOrder(@ModelAttribute Address address,
                              @ModelAttribute ShoppingCart shoppingCart) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        orderService.save(shoppingCart, address, name);
        return "redirect:/order/list";
    }
}
