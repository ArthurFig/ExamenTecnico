package com.mx.drivers.controller;

import com.mx.drivers.dto.Respuesta;
import com.mx.drivers.entity.Driver;
import com.mx.drivers.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    DriverService driverService;

    @GetMapping("/listarConductores")
    public ResponseEntity<List<Driver>> listarConductores() {
        return driverService.listarConductores();
    }

    @PostMapping("/crearConductor")
    public Respuesta crearConductor(@RequestBody Driver driver) {
        return driverService.crearConductor(driver);
    }

    @GetMapping("/conductorPorId")
    public ResponseEntity<Driver> conductorPorId(@RequestParam("Id") int id) {
        return driverService.conductorPorId(id);
    }
}
