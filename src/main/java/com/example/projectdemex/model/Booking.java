package com.example.projectdemex.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false) // Оставил product_id, так как это правильно
    private Product product; // Поле переименовано с tour на product

    @Column(nullable = false, unique = true, length = 20)
    private String bookingCode;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Column(nullable = false)
    private boolean active = true;

    public Booking() {
    }

    public Booking(Product product, String bookingCode) {
        if (product == null || bookingCode == null || bookingCode.isBlank()) {
            throw new IllegalArgumentException("Product and booking code must not be null or empty");
        }
        this.product = product;
        this.bookingCode = bookingCode;
        this.bookingDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        this.product = product;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        if (bookingCode == null || bookingCode.isBlank()) {
            throw new IllegalArgumentException("Booking code must not be null or empty");
        }
        this.bookingCode = bookingCode;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        if (bookingDate == null) {
            throw new IllegalArgumentException("Booking date must not be null");
        }
        this.bookingDate = bookingDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}