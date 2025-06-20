package com.example.projectdemex.controller;

import com.example.projectdemex.model.Product;
import com.example.projectdemex.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("Product", products);
        return "Product";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("Product", new Product());
        return "add_product";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping("/create")
    public String createProduct(@ModelAttribute("Product") Product product,
                                @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageContentType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        }
        productService.createProduct(product);
        return "redirect:/Product";
    }

    @GetMapping("/book/{id}")
    public String bookProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/Product";
        }
        model.addAttribute("product", product);
        return "redirect:/bookings/new?productId=" + id;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/Product";
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null ? product.getImageData() : new byte[0];
    }
}