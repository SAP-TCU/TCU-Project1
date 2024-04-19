package uni.tcu.onlineskishop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.tcu.onlineskishop.model.Product;
import uni.tcu.onlineskishop.repository.IProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void create(Product product) {
        System.out.println("Saving product...");
        productRepository.save(product);
        System.out.println("Product saved!");
    }
}
