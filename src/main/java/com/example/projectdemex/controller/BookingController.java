package com.example.projectdemex.controller;

import com.example.projectdemex.model.Booking;
import com.example.projectdemex.model.Product;
import com.example.projectdemex.service.BookingService;
import com.example.projectdemex.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllActiveBookings());
        return "bookings";
    }

    @GetMapping("/new")
    public String showBookingForm(@RequestParam Long productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "redirect:/Product";
        }

        Booking booking = bookingService.createBooking(product);
        model.addAttribute("product", product);
        model.addAttribute("bookingDate", booking.getBookingDate());
        model.addAttribute("bookingCode", booking.getBookingCode());

        return "booking_confirmation";
    }

    @GetMapping("/{code}")
    public String getBookingDetails(@PathVariable String code, Model model) {
        Booking booking = bookingService.getBookingByCode(code);
        if (booking == null) {
            return "redirect:/bookings";
        }
        model.addAttribute("booking", booking);
        return "booking_details";
    }

    @PostMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings";
    }
}