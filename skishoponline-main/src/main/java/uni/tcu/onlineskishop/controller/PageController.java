package uni.tcu.onlineskishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.tcu.onlineskishop.model.CartItem;
import uni.tcu.onlineskishop.service.CartItemService;
import uni.tcu.onlineskishop.service.CustomerService;
import uni.tcu.onlineskishop.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/pages")
public class PageController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/home")
    public String products() {
        return "home";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartId", customerService.getLoggedCustomerCartOrCreateOne());
        return "shop";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        List<CartItem> cartItems = cartItemService.findAllByCartId(customerService.getLoggedCustomerCartOrCreateOne());
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }
    @GetMapping("/auth/login")
    public String login() {
        return "login";
    }

    @GetMapping("/auth/signup")
    public String signup() {
        return "signup";
    }
}

