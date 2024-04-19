package uni.tcu.onlineskishop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.tcu.onlineskishop.dto.CartItemDTO;
import uni.tcu.onlineskishop.model.Cart;
import uni.tcu.onlineskishop.model.CartItem;
import uni.tcu.onlineskishop.model.Product;
import uni.tcu.onlineskishop.repository.ICartItemRepository;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    public List<CartItem> findAllByCartId(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }

    @Transactional
    public void save(CartItemDTO dto) {
        List<Long> itemsInCart = findAllByCartId(dto.getCartId())
                .stream().map(item -> item.getProduct().getId())
                .toList();
        if (itemsInCart.contains(dto.getProductId())) {
            CartItem existingCartItem = cartItemRepository.findByCartIdAndProductId(dto.getCartId(), dto.getProductId());
            if (existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + dto.getQuantity());
                cartItemRepository.saveAndFlush(existingCartItem);
            }
        } else {
            cartItemRepository.saveAndFlush(mergeDtoToEntity(dto));
        }
        updateCartTotalPrice(dto);
    }

    @Transactional
    public void remove(CartItemDTO dto) {
        cartItemRepository.removeAllByCartIdAndProductId(dto.getCartId(), dto.getProductId());
        Product product = productService.findById(dto.getProductId()).get();
        double minusPrice = product.getPrice() * dto.getQuantity();

        Cart cart = cartService.findById(dto.getCartId()).get();
        cart.setPriceTotal(cart.getPriceTotal() - minusPrice);

        cartService.save(cart);
    }

    private void updateCartTotalPrice(CartItemDTO dto) {
        Product product = productService.findById(dto.getProductId()).get();
        double totalPrice = product.getPrice() * dto.getQuantity();

        Cart cart = cartService.findById(dto.getCartId()).get();
        cart.setPriceTotal(cart.getPriceTotal() + totalPrice);

        cartService.save(cart);
    }

    private CartItem mergeDtoToEntity(CartItemDTO dto) {
        CartItem cartItem = new CartItem();
        if (dto.getProductId() != null) {
            Product product = new Product();
            product.setId(dto.getProductId());
            cartItem.setProduct(product);
        }
        if (dto.getCartId() != null) {
            Cart cart = new Cart();
            cart.setId(dto.getCartId());
            cartItem.setCart(cart);
        }
        cartItem.setQuantity(dto.getQuantity());
        return cartItem;
    }
}
