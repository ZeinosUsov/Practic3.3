package com.example.projectdemex.service;

import com.example.projectdemex.model.Booking;
import com.example.projectdemex.model.Product;
import com.example.projectdemex.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Product product) {
        String bookingCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Booking booking = new Booking(product, bookingCode);
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllActiveBookings() {
        return bookingRepository.findByActiveTrue();
    }

    public Booking getBookingByCode(String code) {
        return bookingRepository.findByBookingCode(code);
    }

    public void cancelBooking(Long id) {
        bookingRepository.findById(id).ifPresent(booking -> {
            booking.setActive(false);
            bookingRepository.save(booking);
        });
    }
}