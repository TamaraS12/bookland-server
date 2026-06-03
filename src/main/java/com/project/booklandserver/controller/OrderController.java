package com.project.booklandserver.controller;

import com.project.booklandserver.dto.OrderDto;
import com.project.booklandserver.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto add(@RequestBody OrderDto order,
                        Authentication authentication) {
        String username = authentication.getName();
        return orderService.add(order, username);
    }

    @GetMapping
    public List<OrderDto> getAll(Authentication authentication){
        String username = authentication.getName();
        return orderService.getAllByUser(username);
    }
}
