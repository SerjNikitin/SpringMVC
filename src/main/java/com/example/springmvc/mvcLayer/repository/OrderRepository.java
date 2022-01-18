package com.example.springmvc.mvcLayer.repository;

import com.example.springmvc.mvcLayer.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findOrdersByUserId_Username(String userName);
}
