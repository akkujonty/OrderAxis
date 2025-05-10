package com.orderaxis.service;

import com.orderaxis.dto.CartItemDto;
import com.orderaxis.entity.CartItem;
import com.orderaxis.entity.Product;
import com.orderaxis.entity.User;
import com.orderaxis.repository.CartItemRepository;
import com.orderaxis.repository.ProductRepository;
import com.orderaxis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public CartItemDto addToCart(CartItemDto cartItemDto) {
        Product product = productRepository.findById(cartItemDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        User user = userRepository.findById(cartItemDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(cartItemDto.getQuantity());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return mapToDto(savedCartItem);
    }

    public CartItemDto updateCartItem(Long id, CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItem.setQuantity(cartItemDto.getQuantity());
        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return mapToDto(updatedCartItem);
    }

    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    public List<CartItemDto> getCartItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartItemRepository.findByUser(user).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CartItemDto mapToDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setId(cartItem.getId());
        dto.setProductId(cartItem.getProduct().getId());
        dto.setUserId(cartItem.getUser().getId());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }
}