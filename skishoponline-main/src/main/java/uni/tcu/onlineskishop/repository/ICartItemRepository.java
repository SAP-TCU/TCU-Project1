package uni.tcu.onlineskishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.tcu.onlineskishop.model.CartItem;

import java.util.List;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCartId(Long cartId);
    void removeAllByCartIdAndProductId(Long cartId, Long productId);
    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
