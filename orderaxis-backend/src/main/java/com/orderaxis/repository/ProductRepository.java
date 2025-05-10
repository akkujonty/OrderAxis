package com.orderaxis.repository;

import com.orderaxis.entity.Product;
import com.orderaxis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(User seller);
}