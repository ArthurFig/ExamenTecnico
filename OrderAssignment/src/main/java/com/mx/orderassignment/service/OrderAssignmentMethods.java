package com.mx.orderassignment.service;

import com.mx.orderassignment.dto.Respuesta;
import org.springframework.web.multipart.MultipartFile;

public interface OrderAssignmentMethods {
    public Respuesta asignarOrden(int orderId, int driverId, MultipartFile pdf, MultipartFile image);
}
