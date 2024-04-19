package uni.tcu.onlineskishop.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uni.tcu.onlineskishop.security.handler.AuthSuccessHandler;
import uni.tcu.onlineskishop.security.service.CustomerDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/customers/save").permitAll();
                    registry.requestMatchers("/pages/**").permitAll();
                    registry.requestMatchers("/pages/auth/**").permitAll();
                    registry.requestMatchers("/pages/auth/login").permitAll();
                    registry.requestMatchers("/pages/signup").permitAll();
                    registry.requestMatchers("css/**", "js/**").permitAll();
                    registry.anyRequest().authenticated();
                }).formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/pages/auth/login")
                            .successHandler(new AuthSuccessHandler())
                            .permitAll();
                }).build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customerDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
