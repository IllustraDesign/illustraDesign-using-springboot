package com.illustraDesign.ecommerce.store.repository;

import com.illustraDesign.ecommerce.store.model.admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminRepository extends JpaRepository<admin, Long> {
    admin findByUsername(String username);
}
