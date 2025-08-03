package com.mx.drivers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    private String mensaje;
    private boolean estado;
    private Object objeto;
    private LocalDateTime fecha;
}
