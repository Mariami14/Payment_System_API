package com.marie.paymentsystem.model.repository;

import com.marie.paymentsystem.model.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
}
