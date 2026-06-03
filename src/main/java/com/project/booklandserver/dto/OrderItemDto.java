package com.project.booklandserver.dto;

import java.math.BigDecimal;

public record OrderItemDto(Long id,
                           Long bookId,
                           String bookTitle,
                           Integer quantity,
                           BigDecimal amount) {
}
