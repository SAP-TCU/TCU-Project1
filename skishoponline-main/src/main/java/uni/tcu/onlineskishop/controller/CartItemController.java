package uni.tcu.onlineskishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.tcu.onlineskishop.dto.CartItemDTO;
import uni.tcu.onlineskishop.model.CartItem;
import uni.tcu.onlineskishop.service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/items/{cart-id}")
    public List<CartItem> allInCart(@PathVariable("cart-id") Long cartId) {
        return cartItemService.findAllByCartId(cartId);
    }

    @PostMapping("/save")
    public void saveItem(@RequestBody CartItemDTO dto) {
        cartItemService.save(dto);
    }

    @PostMapping("/remove")
    public void removeItem(@RequestBody CartItemDTO dto) {
        cartItemService.remove(dto);
    }
}
