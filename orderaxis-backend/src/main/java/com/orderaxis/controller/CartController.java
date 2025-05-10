package com.orderaxis.controller;

import com.orderaxis.dto.CartItemDto;
import com.orderaxis.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartItemDto> addToCart(@Valid @RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartService.addToCart(cartItemDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long id, @Valid @RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartService.updateCartItem(id, cartItemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getCartItems(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
}