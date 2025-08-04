package com.mx.orderassignment.service;

import com.mx.orderassignment.Feign.DriverFeign;
import com.mx.orderassignment.Feign.OrderFeign;
import com.mx.orderassignment.dao.OrderAssignmentDao;
import com.mx.orderassignment.dto.DriverModel;
import com.mx.orderassignment.dto.OrderModel;
import com.mx.orderassignment.dto.Respuesta;
import com.mx.orderassignment.entity.OrderAssignment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class OrderAssignmentService implements OrderAssignmentMethods{

    private OrderAssignmentDao orderAssignmentDao;
    private DriverFeign driverFeign;
    private OrderFeign orderFeign;

    public OrderAssignmentService(OrderAssignmentDao orderAssignmentDao, OrderFeign orderFeign, DriverFeign driverFeign) {
        this.orderAssignmentDao = orderAssignmentDao;
        this.driverFeign = driverFeign;
        this.orderFeign = orderFeign;
    }

    public Respuesta respuesta(String mensaje, boolean estado, Object objeto, LocalDateTime fecha) {
        return new Respuesta(mensaje, estado, objeto, LocalDateTime.now());
    }

    private final String UPLOAD_DIR = "/app/uploads";

    @Override
    public Respuesta asignarOrden(int orderId, int driverId, MultipartFile pdf, MultipartFile image) {
        try {
            OrderModel order = orderFeign.ordenPorId(orderId).getBody();
            DriverModel driver = driverFeign.conductorPorId(driverId).getBody();

            if (order == null || driver == null) {
                return respuesta("Orden o conductor no encontrado", false, null, null);
            }

            if (!Objects.equals(driver.getActive(), "true")) {
                return respuesta("El conductor no está activo", false, driver, null);
            }

            if (!"CREATED".equalsIgnoreCase(order.getStatus())) {
                return respuesta("La orden no está en estado 'CREATED'", false, order, null);
            }

            String pdfPath = saveFile(pdf, "/pdfs/");
            String imagePath = saveFile(image, "/images/");

            OrderAssignment assignment = new OrderAssignment();
            assignment.setOrderId(orderId);
            assignment.setDriverId(driverId);
            assignment.setPdfPath(pdfPath);
            assignment.setImagePath(imagePath);

            orderAssignmentDao.save(assignment);

            return respuesta("Asignación realizada correctamente", true, assignment, null);

        } catch (Exception e) {
            return respuesta(e.getMessage(), false, null, null);
        }
    }

    private String saveFile(MultipartFile file, String subDir) throws IOException {
        if (file == null || file.isEmpty()) return null;

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String path = UPLOAD_DIR + subDir;
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();

        String fullPath = path + fileName;
        file.transferTo(new File(fullPath));

        return fullPath;
    }
}

