package com.mx.drivers.service;

import com.mx.drivers.dao.DriverDao;
import com.mx.drivers.dto.Respuesta;
import com.mx.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DriverService implements DriverGetMethods, DriverPostMethods{
    @Autowired
    private DriverDao driverDao;

    public DriverService(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public Respuesta respuesta(String mensaje, boolean estado, Object objeto, LocalDateTime fecha){
        return new Respuesta(mensaje,estado,objeto,LocalDateTime.now());
    }

    @Override
    public ResponseEntity<List<Driver>> listarConductores() {
        try{
            if (driverDao.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(driverDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Respuesta crearConductor(Driver driver) {
        try {
            if (driverDao.existsById(driver.getId())) {
                return respuesta("El ID del conductor ya existe", false, driver, null);
            }
            driverDao.save(driver);
            return respuesta("El conductor fue creado correctamente", true, driver, null);
        }catch(Exception e){
            return respuesta(e.getMessage(),false,driver,null);
        }
    }
}
