package com.mx.drivers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DRIVERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @Positive(message = "El ID debe de ser un numero positivo")
    @NotNull(message = "El ID no debe ser nulo o vacio")
    private int id;
    @NotNull(message = "El name no debe ser nulo o vacio")
    private String name;
    @Column(name = "LICENSE")
    @NotNull(message = "El licenseNumber no debe ser nulo o vacio")
    private String licenseNumber;
    private String active;
}
