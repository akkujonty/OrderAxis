package com.orderaxis.controller;

import com.orderaxis.dto.OrderDto;
import com.orderaxis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.placeOrder(orderDto));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}