package com.illustraDesign.ecommerce.store.service;

import com.illustraDesign.ecommerce.store.model.admin;
import com.illustraDesign.ecommerce.store.repository.adminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminDetailsService implements UserDetailsService {

    @Autowired
    private adminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found");
        }

        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }
}
