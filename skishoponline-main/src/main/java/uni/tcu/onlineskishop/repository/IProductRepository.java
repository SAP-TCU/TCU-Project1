package uni.tcu.onlineskishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.tcu.onlineskishop.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
