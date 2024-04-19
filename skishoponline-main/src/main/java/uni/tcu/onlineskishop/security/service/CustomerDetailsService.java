package uni.tcu.onlineskishop.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uni.tcu.onlineskishop.enums.CustomerType;
import uni.tcu.onlineskishop.model.Customer;
import uni.tcu.onlineskishop.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            Customer foundCustomer = customer.get();
            return User.builder()
                    .username(foundCustomer.getUsername())
                    .password(foundCustomer.getPassword())
                    .roles(getRoles(foundCustomer.getType()))
                    .build();
        }
        throw new UsernameNotFoundException(username + " not found!");
    }

    private String[] getRoles(CustomerType customerType) {
        List<String> roles = new ArrayList<>();
        if (customerType == CustomerType.EMPLOYEE) {
            roles.add("Employee");
        }
        roles.add("Client");
        return roles.toArray(String[]::new);
    }
}
