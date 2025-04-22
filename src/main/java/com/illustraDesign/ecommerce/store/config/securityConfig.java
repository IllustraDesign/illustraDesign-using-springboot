package com.illustraDesign.ecommerce.store.config;

import com.illustraDesign.ecommerce.store.service.adminDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(adminDetailsService adminDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           DaoAuthenticationProvider authProvider) throws Exception {
        http
          .authenticationProvider(authProvider)
          .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/login", "/css/**", "/js/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
          )
          .formLogin(form -> form
            .loginPage("/admin/login")
            .defaultSuccessUrl("/admin/dashboard", true)
            .failureUrl("/admin/login?error=true")
          )
          .logout(logout -> logout
            .logoutUrl("/admin/logout")
            .logoutSuccessUrl("/admin/login?logout=true")
          );
        return http.build();
    }
}
