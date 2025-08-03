package com.mx.order.controller;

import com.mx.order.dto.Respuesta;
import com.mx.order.entity.Order;
import com.mx.order.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/ordenPorId")
    public ResponseEntity<Order> ordenPorId(@RequestParam("Id") int id) {
        return orderService.ordenPorId(id);
    }

    @GetMapping("/ordenPorStatus")
    public ResponseEntity<List<Order>> ordenPorStatus(@RequestParam("status") String status) {
        return orderService.ordenPorStatus(status);
    }

    @GetMapping("/ordenPorOrigen")
    public ResponseEntity<List<Order>> ordenPorOrigen(@RequestParam("origen") String origen) {
        return orderService.ordenPorOrigen(origen);
    }

    @GetMapping("/ordenPorDestino")
    public ResponseEntity<List<Order>> ordenPorDestino(@RequestParam("destino") String destino) {
        return orderService.ordenPorDestino(destino);
    }

    @GetMapping("/ordenPorCreacion")
    public ResponseEntity<List<Order>> ordenPorCreacion(@RequestParam("createdAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAt) {
        return orderService.ordenPorCreacion(createdAt);
    }

    @GetMapping("/ordenPorActualizacion")
    public ResponseEntity<List<Order>> ordenPorActualizacion(@RequestParam("updatedAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedAt) {
        return orderService.ordenPorActualizacion(updatedAt);
    }

    @PostMapping("/crearOrden")
    public Respuesta crearOrden(@Valid @RequestBody Order orden){
        return orderService.crearOrden(orden);
    }

    @PutMapping("/actualizarEstadoOrden")
    public Respuesta actualizarEstadoOrden(@Valid @RequestBody Order order){
        return orderService.actualizarEstadoOrden(order.getId(), order.getStatus());
    }
}
