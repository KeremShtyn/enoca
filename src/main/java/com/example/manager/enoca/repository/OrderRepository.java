package com.example.manager.enoca.repository;

import com.example.manager.enoca.domain.Order;
import com.example.manager.enoca.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    Optional<OrderEntity> findByOrderCode(String orderCode);

    List<Order> findByUserId(String userId);
}
