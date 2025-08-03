package com.mx.orderassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverModel {
    private int id;
    private String name;
    private String licenseNumber;
    private String active;
}
