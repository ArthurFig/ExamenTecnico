package com.mx.drivers.service;

import com.mx.drivers.entity.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverGetMethods {
    public ResponseEntity<List<Driver>> listarConductores();
}
