package com.mx.order.dao;

import com.mx.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
    List<Order> findByStatus(String status);
    List<Order> findByOrigin(String origin);
    List<Order> findByDestination(String destination);
    List<Order> findByCreatedAt(LocalDateTime createdAt);
    List<Order> findByUpdatedAt(LocalDateTime updatedAt);
}
