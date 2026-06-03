package com.project.booklandserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String address;
    private BigDecimal totalAmount;
    private LocalDate orderDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            targetEntity = OrderItem.class,
            orphanRemoval = true
    )
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String country, String city, String address, BigDecimal totalAmount) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.totalAmount = totalAmount;
    }

    public Order() {
    }

}
