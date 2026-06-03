package com.project.booklandserver.service;

import com.project.booklandserver.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto add(OrderDto orderDto, String username);

    List<OrderDto> getAllByUser(String username);
}
