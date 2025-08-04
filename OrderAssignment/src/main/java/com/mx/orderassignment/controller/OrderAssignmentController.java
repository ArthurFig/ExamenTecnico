package com.mx.orderassignment.controller;

import com.mx.orderassignment.dto.Respuesta;
import com.mx.orderassignment.service.OrderAssignmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/orderAssignment")
@SecurityRequirement(name = "bearerAuth")
public class OrderAssignmentController {

    @Autowired
    private OrderAssignmentService orderAssignmentService;

    /**
     *
     *
     * @param orderId ID de la orden
     * @param driverId ID del conductor
     * @param pdf archivo PDF obligatorio
     * @param image archivo de imagen opcional (.jpg, .png)
     * @return Respuesta personalizada con detalles del proceso
     */

    @PostMapping(value = "/asignarOrden", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Respuesta asignarOrden(
            @RequestParam("orderId") int orderId,
            @RequestParam("driverId") int driverId,
            @RequestPart("pdf") MultipartFile pdf,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return orderAssignmentService.asignarOrden(orderId, driverId, pdf, image);
    }
}

