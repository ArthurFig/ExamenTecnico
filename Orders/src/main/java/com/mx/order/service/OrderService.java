package com.mx.order.service;

import com.mx.order.dao.OrderDao;
import com.mx.order.dto.Respuesta;
import com.mx.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements OrderGetMethods, OrderPostMethods{

    @Autowired
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Respuesta respuesta(String mensaje, boolean estado, Object objeto, LocalDateTime fecha) {
        return new Respuesta(mensaje, estado, objeto, LocalDateTime.now());
    }

    @Override
    public ResponseEntity<Order> ordenPorId(int id) {
        try{
            Order order = orderDao.findById(id).orElse(null);
            if(order == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Order>> ordenPorStatus(String status) {
        try{
            String statusAux = status.toUpperCase();
            List<Order> orders = orderDao.findByStatus(statusAux);
            if(orders == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Order>> ordenPorOrigen(String origen) {
        try{
            List<Order> orders = orderDao.findByOrigin(origen);
            if(orders == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Order>> ordenPorDestino(String destino) {
        try{
            List<Order> orders = orderDao.findByDestination(destino);
            if(orders == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Order>> ordenPorCreacion(LocalDateTime createdAt) {
        try{
            List<Order> orders = orderDao.findByCreatedAt(createdAt);
            if(orders == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Order>> ordenPorActualizacion(LocalDateTime updatedAt) {
        try{
            List<Order> orders = orderDao.findByUpdatedAt(updatedAt);
            if(orders == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Respuesta crearOrden(Order order) {
        try{
            if (orderDao.existsById(order.getId())) {
                return respuesta("La orden con este ID ya existe", false, order, null);
            }
            orderDao.save(order);
            return respuesta("La orden se creo correctamente", true, order, null);
        } catch (Exception e) {
            return respuesta(e.getMessage(), false, order, null);
        }
    }

    @Override
    public Respuesta actualizarEstadoOrden(int idOrden, String estado) {
        try{
            Order order = orderDao.findById(idOrden).orElse(null);
            if(order == null){
                return respuesta("La orden no existe", false, order, null);
            }
            order.setStatus(estado);
            orderDao.save(order);
            return respuesta("El estado en la orden fue actualizado correctamente", true, order, null);
        } catch (Exception e) {
            return respuesta(e.getMessage(), false, idOrden, null);
        }
    }
}
