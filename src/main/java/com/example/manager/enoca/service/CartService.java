package com.example.manager.enoca.service;

import com.example.manager.authentication.domain.User;
import com.example.manager.authentication.entity.UserEntity;
import com.example.manager.authentication.error.ErrorCodes;
import com.example.manager.authentication.mapper.UserMapper;
import com.example.manager.authentication.repository.UserRepository;
import com.example.manager.authentication.service.UserService;
import com.example.manager.enoca.domain.Cart;
import com.example.manager.enoca.domain.Product;
import com.example.manager.enoca.entity.CartEntity;
import com.example.manager.enoca.entity.ProductEntity;
import com.example.manager.enoca.mapper.CartMapper;
import com.example.manager.enoca.mapper.ProductMapper;
import com.example.manager.enoca.repository.CartRepository;
import com.example.manager.enoca.repository.ProductRepository;
import com.example.manager.util.exception.EnocaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class CartService {

    private CartMapper cartMapper;

    private CartRepository cartRepository;

    private UserService userService;

    private ProductService productService;

    public CartService(CartMapper cartMapper, CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartMapper = cartMapper;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public Cart getCartByUserId(String userId){
        User user = userService.findById(userId);
        return cartMapper.toDomainObject(cartRepository.findByUserId(userId).orElseThrow(() -> new EnocaException(ErrorCodes.DATA_NOT_FOUND)));
    }


    @Transactional
    public Cart updateCart(String userId, String productId, int quantity){
        Cart cart = cartMapper.toDomainObject(cartRepository.findByUserId(userId).orElseThrow(() -> new EnocaException(ErrorCodes.THIS_USER_DOES_NOT_EXIST)));
        Product product = productService.findById(productId);
        if (product.getStock() < quantity){
            throw new EnocaException(ErrorCodes.PRODUCT_QUANTITY_IS_NOT_ENOUGH);
        }
        cart.getProducts().put(product, quantity);
        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * quantity));
        cart.setQuantity(cart.getQuantity() + quantity);
        return cart;
    }

    public void emptyCart(String userId){
        Cart cart = cartMapper.toDomainObject(cartRepository.findByUserId(userId).orElseThrow(() -> new EnocaException(ErrorCodes.THIS_USER_DOES_NOT_EXIST)));
        cart.setQuantity(0);
        cart.getProducts().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cartMapper.toEntity(cart));
    }

    @Transactional
    public Cart removeFromCart(String userId, String productId, int quantity){
        Cart cart = cartMapper.toDomainObject(cartRepository.findByUserId(userId).orElseThrow(() -> new EnocaException(ErrorCodes.THIS_USER_DOES_NOT_EXIST)));
        Product product = productService.findById(productId);
        if (product.getStock() < quantity){
            throw new EnocaException(ErrorCodes.PRODUCT_QUANTITY_IS_NOT_ENOUGH);
        }
        cart.getProducts().remove(product, quantity);
        cart.setTotalPrice(cart.getTotalPrice() - (product.getPrice() * quantity));
        cart.setQuantity(cart.getQuantity() - quantity);
        return cart;
    }
}
