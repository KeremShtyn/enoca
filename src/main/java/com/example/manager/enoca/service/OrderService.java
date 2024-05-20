package com.example.manager.enoca.service;

import com.example.manager.authentication.domain.User;
import com.example.manager.authentication.entity.UserEntity;
import com.example.manager.authentication.error.ErrorCodes;
import com.example.manager.authentication.mapper.UserMapper;
import com.example.manager.authentication.repository.UserRepository;
import com.example.manager.authentication.service.UserService;
import com.example.manager.enoca.domain.Cart;
import com.example.manager.enoca.domain.Order;
import com.example.manager.enoca.domain.Product;
import com.example.manager.enoca.entity.OrderEntity;
import com.example.manager.enoca.mapper.OrderMapper;
import com.example.manager.enoca.repository.OrderRepository;
import com.example.manager.util.exception.EnocaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    private UserService userService;

    private CartService cartService;

    private ProductService productService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserService userService, CartService cartService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Transactional
    public Order placeOrder(String username) {
        User user = userService.findByUsername(username);
        Cart cart = user.getCart();
        String orderCode = generateOrderCode();

        try {

            cart.getProducts().forEach((product, quantity) -> {
                Product domainObject = productService.findById(product.getId());

                int newStock = domainObject.getStock() - quantity;
                if (newStock < 0) {
                    throw new EnocaException(ErrorCodes.PRODUCT_QUANTITY_IS_NOT_ENOUGH , domainObject.getName());
                }

                domainObject.setStock(newStock);
                productService.createProduct(domainObject);
            });

            Order order = new Order();
            order.setProducts(cart.getProducts());
            order.setTotalPrice(cart.getTotalPrice());
            order.setUser(user);
            order.setOrderCode(orderCode);
            OrderEntity orderEntity = orderMapper.toEntity(order);
            orderEntity= orderRepository.save(orderEntity);
            return orderMapper.toDomainObject(orderEntity);
        }catch (Exception e){
            System.err.println("An error occurred while placing the order: " + e.getMessage());
            throw e;
        }

    }

    public Order findByOrderCode(String orderCode){
        OrderEntity order = orderRepository.findByOrderCode(orderCode).orElseThrow(() -> new EnocaException(ErrorCodes.ORDER_DOES_NOT_EXIST));
        Order domainObject = orderMapper.toDomainObject(order);
        return domainObject;
    }

    public List<Order> findOrdersByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }


    public String generateOrderCode() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String datePart = formatter.format(new Date());
        String randomPart = UUID.randomUUID().toString().substring(0, 6);
        return datePart + randomPart;
    }


}
