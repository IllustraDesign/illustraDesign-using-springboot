package com.illustraDesign.ecommerce.store.controller;

import com.illustraDesign.ecommerce.store.model.admin;
import com.illustraDesign.ecommerce.store.model.product;
import com.illustraDesign.ecommerce.store.repository.adminRepository;
import com.illustraDesign.ecommerce.store.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private adminRepository adminRepository;

    // Inject the ProductRepository so you can call save() on it
    @Autowired
    private productRepository productRepository;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("admin", new admin());
        return "admin-login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute admin admin, HttpSession session, Model model) {
        admin existingAdmin = adminRepository.findByUsername(admin.getUsername());
        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            session.setAttribute("adminUser", existingAdmin);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "Invalid credentials");
        return "admin-login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute product product,
                             @RequestParam("productImage") MultipartFile file,
                             HttpServletRequest request) throws IOException {
        String uploadDir = "src/main/resources/static/product-images";

        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());           // ensure directory exists
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            product.setImageName(fileName);
        }

        productRepository.save(product);
        return "redirect:/admin/products";
    }
}
