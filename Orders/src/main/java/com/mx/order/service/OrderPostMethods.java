package com.mx.order.service;

import com.mx.order.dto.Respuesta;
import com.mx.order.entity.Order;

public interface OrderPostMethods {
    public Respuesta crearOrden(Order order);
    public Respuesta actualizarEstadoOrden(int idOrden, String estado);
}
