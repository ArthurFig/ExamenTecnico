package com.mx.orderassignment.Feign;

import com.mx.orderassignment.dto.OrderModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Orders", url = "http://localhost:9001", path = "/orders")
public interface OrderFeign {
    @GetMapping("/ordenPorId")
    public ResponseEntity<OrderModel> ordenPorId(@RequestParam("Id") int id);
}
