package uni.tcu.onlineskishop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.tcu.onlineskishop.model.Cart;
import uni.tcu.onlineskishop.repository.ICartRepository;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ICartRepository cartRepository;

    @Transactional
    public void save(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    @Transactional
    public void saveWithoutFlush(Cart cart) {
        cartRepository.save(cart);
    }

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }
}
