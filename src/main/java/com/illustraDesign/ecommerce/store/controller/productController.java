package com.illustraDesign.ecommerce.store.controller;

import com.illustraDesign.ecommerce.store.model.product;
import com.illustraDesign.ecommerce.store.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class productController {

    @Autowired
    private productService productService;

    // Show list of all products
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

    // Show add product form
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new product());
        return "product-form";
    }

    // Save product to database
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    // Delete product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
}
