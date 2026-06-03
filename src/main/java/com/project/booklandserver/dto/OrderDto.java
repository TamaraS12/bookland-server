package com.project.booklandserver.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDto(Long id,
                       String country,
                       String city,
                       String address,
                       List<OrderItemDto> items,
                       BigDecimal totalAmount,
                       LocalDate orderDate) {
}
