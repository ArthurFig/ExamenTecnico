package com.mx.orderassignment.Feign;

import com.mx.orderassignment.dto.DriverModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Drivers", url = "http://localhost:9002", path = "/drivers")
public interface DriverFeign {
    @GetMapping("/conductorPorId")
    public ResponseEntity<DriverModel> conductorPorId(@RequestParam("Id") int id);
}
