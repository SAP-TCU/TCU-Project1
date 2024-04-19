package uni.tcu.onlineskishop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.tcu.onlineskishop.enums.CustomerType;
import uni.tcu.onlineskishop.model.Customer;
import uni.tcu.onlineskishop.service.CustomerService;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    public CustomerController(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/save")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        Optional<Customer> existingCustomer = customerService.findByUsername(customer.getUsername());
        if (existingCustomer.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        if (customer.getType() == null) {
            customer.setType(CustomerType.CLIENT);
        }

        customerService.registerCustomer(customer);
        return ResponseEntity.ok("Customer registered successfully");
    }
}
