package com.project.booklandserver.service.impl;

import com.project.booklandserver.dto.OrderDto;
import com.project.booklandserver.mapper.OrderItemMapper;
import com.project.booklandserver.mapper.OrderMapper;
import com.project.booklandserver.model.Book;
import com.project.booklandserver.model.Order;
import com.project.booklandserver.model.OrderItem;
import com.project.booklandserver.model.User;
import com.project.booklandserver.repository.BookRepository;
import com.project.booklandserver.repository.OrderRepository;
import com.project.booklandserver.repository.UserRepository;
import com.project.booklandserver.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;
    private final OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, BookRepository bookRepository, OrderItemMapper orderItemMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.bookRepository = bookRepository;
        this.orderItemMapper = orderItemMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OrderDto add(OrderDto orderDto, String username) {
        Order order = orderMapper.toEntity(orderDto);

        List<OrderItem> orderItems = orderDto.items().stream()
                .map(item -> {
                    OrderItem orderItemEntity = orderItemMapper.toEntity(item);
                    Book bookEntity = bookRepository.findById(item.bookId())
                            .orElseThrow(() -> new RuntimeException("Book not found"));

                    orderItemEntity.setBook(bookEntity);
                    return orderItemEntity;
                })
                .toList();

        order.setItems(orderItems);
        order.setOrderDate(LocalDate.now());

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderDto> getAllByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        List<Order> orders = orderRepository.getAllByUserId(user.getId());
        return orders.stream().map(order -> orderMapper.toDto(order)).toList();
    }
}
