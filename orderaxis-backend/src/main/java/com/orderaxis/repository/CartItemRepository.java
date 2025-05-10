package com.orderaxis.repository;

import com.orderaxis.entity.CartItem;
import com.orderaxis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}