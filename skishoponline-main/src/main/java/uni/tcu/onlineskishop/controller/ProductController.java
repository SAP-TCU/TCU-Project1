package uni.tcu.onlineskishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.tcu.onlineskishop.model.Product;
import uni.tcu.onlineskishop.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Product product) {
        productService.create(product);
    }
}
