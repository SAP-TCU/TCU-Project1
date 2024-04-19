package uni.tcu.onlineskishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.tcu.onlineskishop.model.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
}
