package com.example.manager.enoca.controller;

import com.example.manager.enoca.api.CartApi;
import com.example.manager.enoca.domain.Cart;
import com.example.manager.enoca.dto.CartDTO;
import com.example.manager.enoca.mapper.CartDTOMapper;
import com.example.manager.enoca.service.CartService;
import com.example.manager.util.response.EnocaGenerator;
import com.example.manager.util.response.EnocaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class CartController implements CartApi {

    private CartService cartService;

    private CartDTOMapper cartDTOMapper;

    public CartController(CartService cartService, CartDTOMapper cartDTOMapper) {
        this.cartService = cartService;
        this.cartDTOMapper = cartDTOMapper;
    }


    @Override
    public EnocaResponse<Object> getByUserId(String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return EnocaGenerator.generateSignResponse(cartDTOMapper.toDTO(cart));
    }


    @Override
    public EnocaResponse<Object> updateCart(String userId, String productId, int quantity) {
        Cart cart = cartService.updateCart(userId, productId, quantity);
        return EnocaGenerator.generateSignResponse(cart);
    }

    @Override
    public EnocaResponse<Object> removeFromCart(String userId, String productId, int quantity) {
        Cart cart = cartService.removeFromCart(userId, productId, quantity);
        return EnocaGenerator.generateSignResponse(cart);
    }

    @Override
    public void emptyCart(String userId) {
        cartService.emptyCart(userId);
    }
}
