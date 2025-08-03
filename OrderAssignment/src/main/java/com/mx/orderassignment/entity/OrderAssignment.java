package com.mx.orderassignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDER_ASSIGNMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int orderId;
    @NotNull
    private int driverId;
    private String pdfPath;
    private String imagePath;
}
