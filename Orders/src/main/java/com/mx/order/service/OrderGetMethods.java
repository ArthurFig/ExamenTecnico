package com.mx.order.service;

import com.mx.order.entity.Order;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderGetMethods {
    public ResponseEntity<Order> ordenPorId(int id);
    public ResponseEntity<List<Order>> ordenPorStatus(String status);
    public ResponseEntity<List<Order>> ordenPorOrigen(String origen);
    public ResponseEntity<List<Order>> ordenPorDestino(String destino);
    public ResponseEntity<List<Order>> ordenPorCreacion(LocalDateTime createdAt);
    public ResponseEntity<List<Order>> ordenPorActualizacion(LocalDateTime updatedAt);
}
