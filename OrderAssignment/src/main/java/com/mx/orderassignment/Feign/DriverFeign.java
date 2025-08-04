package com.mx.orderassignment.Feign;

import com.mx.orderassignment.dto.DriverModel;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "drivers-service",url = "http://drivers-service:9002", path = "/drivers")
public interface DriverFeign {
    @GetMapping(value = "/conductorPorId")
    public ResponseEntity<DriverModel> conductorPorId(@RequestParam("Id") @Min(1) int id);
}
