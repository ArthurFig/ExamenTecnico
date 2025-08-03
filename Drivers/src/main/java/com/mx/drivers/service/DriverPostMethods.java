package com.mx.drivers.service;

import com.mx.drivers.dto.Respuesta;
import com.mx.drivers.entity.Driver;

public interface DriverPostMethods {
    public Respuesta crearConductor(Driver driver);
}
