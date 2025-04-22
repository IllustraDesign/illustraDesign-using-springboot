package com.illustraDesign.ecommerce.store.repository;

import com.illustraDesign.ecommerce.store.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<product, Long> {
    // You can add custom queries here later if needed
}
