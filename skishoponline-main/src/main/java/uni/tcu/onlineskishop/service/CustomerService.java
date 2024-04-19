package uni.tcu.onlineskishop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uni.tcu.onlineskishop.model.Cart;
import uni.tcu.onlineskishop.model.Customer;
import uni.tcu.onlineskishop.repository.ICustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CartService cartService;

    public Optional<Customer> getLoggedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            UserDetails customerDetails = (UserDetails) authentication.getPrincipal();
            return customerRepository.findByUsername(customerDetails.getUsername());
        }
        return Optional.empty();
    }

    @Transactional
    public Long getLoggedCustomerCartOrCreateOne() {
        Optional<Customer> customer = getLoggedCustomer();
        if (customer.isPresent()) {
            Customer foundCustomer = customer.get();

            if (foundCustomer.getCart() != null) {
                return foundCustomer.getCart().getId();
            }

            Cart cart = createNewCart(foundCustomer);
            cartService.saveWithoutFlush(cart);

            foundCustomer.setCart(cart);
            customerRepository.saveAndFlush(foundCustomer);

            return foundCustomer.getCart().getId();
        }
        throw new NullPointerException();
    }

    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    private Cart createNewCart(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setPriceTotal(0);
        return cart;
    }
}
